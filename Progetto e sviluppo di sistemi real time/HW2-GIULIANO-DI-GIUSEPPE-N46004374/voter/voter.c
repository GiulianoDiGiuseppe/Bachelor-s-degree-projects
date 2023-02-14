//-------GIULIANO-DI-GIUSEPPE-N46004374
//TODO: Aggiungere header file necessari
#include <linux/module.h>
#include <linux/moduleparam.h>
#include <asm/io.h>
#include <linux/kernel.h>
#include <linux/init.h>
#include <linux/stat.h>
#include <asm/rtai.h>
#include <rtai_shm.h>
#include <rtai_sem.h>
#include <rtai_msg.h>
#include <rtai_mbx.h>
#include <rtai_sched.h>
#include "parameters.h"


//TODO: definizioni variabili globali e puntatori alle shared memory
	//TIME
	static RTIME tick_period,fase;

	//TASK
	static RT_TASK altitude_task;
	static RT_TASK speeds_task;
	static RT_TASK temperature_task;
	
	//SEM
	static SEM* mutex_raw_temp;
	static SEM* mutex_raw_alt;
	static SEM* mutex_raw_speed;
	static SEM* mutex_pro_temp; 
	static SEM* mutex_pro_alt;
	static SEM* mutex_pro_speed;

	//MBX
	static MBX * mbx_stop_alt;
	static MBX * mbx_stop_speed;
	static MBX * mbx_stop_temp;
	static MBX * mbx_ape_req;

	//STRUCT
	static struct raw_sensors_data *p_raw_sensors_data;
	static struct processed_sensors_data *p_processed_sensors_data;

	//VAR
		static long altitude = 1;
		module_param(altitude, long, 0664);
		static long speed = 1;
		module_param(speed, long, 0664);
		static long temperature = 1;
		module_param(temperature, long, 0664);

	//SLACK & OVERRUN
		static int overrun = 0;
		long long slack = 0;
		int i;
	int stop;
	
//TODO: implementazione funzioni dei task per temperature, speed, altitude

//ALTITUDE
	static void function_altitude(long altitude)
	{
		if(altitude == 0){
			return;
		}
		
		while (1)
		{
			stop=0;
			//MBX_STOP_REQ
			rt_mbx_receive_if(mbx_stop_alt, &stop, sizeof(int));
			
			//MUTEX SU PROCESS
			if(stop == ID_ALT){
				rt_sem_wait(mutex_pro_alt);
				p_processed_sensors_data->altitude = -1;
				rt_sem_signal(mutex_pro_alt);
				return;
			}
		
		// WAIT SU RAW E PRO
			rt_sem_wait(mutex_raw_alt);
			rt_sem_wait(mutex_pro_alt);
		
		//VALORE ERRATO
			for(i=0; i < ALTITUDE_SIZE - 1; i++){
				if(p_raw_sensors_data->altitudes[i] == 0 || p_raw_sensors_data->altitudes[i] != p_raw_sensors_data->altitudes[i+1]){
					int msg = ID_ALT;
					rt_mbx_send(mbx_ape_req, &msg, sizeof(int));
				}	
			}
		
		//VALORE CORRETTO
			for (i = 0; i < ALTITUDE_SIZE ; i++){
				if (p_raw_sensors_data->altitudes[i] != 0 && p_raw_sensors_data->altitudes[i] == p_raw_sensors_data->altitudes[(i + 1)%ALTITUDE_SIZE]){
				//VALORE IN PROD
					p_processed_sensors_data->altitude = p_raw_sensors_data->altitudes[i];
					
				//CALCOLO SLACK E OVERRUN
					slack = count2nano(next_period() - rt_get_time());
					if(slack<0){
					rt_printk("TASK ALTITUDE SLACK TIME ERROR %d\n", rt_whoami());
					}
					overrun = rt_task_wait_period();
					if(overrun == RTE_TMROVRN){
						rt_printk("TASK ALTITUDE OVERRUN %d\n", rt_whoami());
					}
				}
			}
					rt_sem_signal(mutex_pro_alt);
					rt_sem_signal(mutex_raw_alt);				
		}
	}

//SPEED
	static void function_speeds(int t)
	{
		if(speed == 0){
			return;
		}
		while (1)
			stop=0;
			//MBX_STOP_REQ
			rt_mbx_receive_if(mbx_stop_speed, &stop, sizeof(int));
			//MUTEX SU PROCESS
			if(stop == ID_SPEED){
				rt_sem_wait(mutex_pro_speed);
				p_processed_sensors_data->speed = -1;
				rt_sem_signal(mutex_pro_speed);
				return ;
			}
		
		// WAIT SU RAW E PRO
		rt_sem_wait(mutex_raw_speed);
		rt_sem_wait(mutex_pro_speed);

		//VALORE ERRATO
		for(i=0; i < SPEED_SIZE - 1; i++){
			if(p_raw_sensors_data->speeds[i] != p_raw_sensors_data->speeds[i+1]){
				int msg = ID_SPEED;
				rt_mbx_send(mbx_ape_req, &msg, sizeof(int));
			}	
		}
		//VALORE CORRETTO
		for (i = 0; i < SPEED_SIZE ; i++){
			if (p_raw_sensors_data->speeds[i] == p_raw_sensors_data->speeds[i + 1] || p_raw_sensors_data->speeds[i] == p_raw_sensors_data->speeds[(i + 2)%SPEED_SIZE]){
				//VALORE IN PROD
				p_processed_sensors_data->speed = p_raw_sensors_data->speeds[i];
				//CALCOLO SLACK E OVERRUN
					slack = count2nano(next_period() - rt_get_time());
					if(slack<0){
					rt_printk("TASK ALTITUDE SLACK TIME ERROR %d\n", rt_whoami());
					}
					overrun = rt_task_wait_period();
					if(overrun == RTE_TMROVRN){
						rt_printk("TASK ALTITUDE OVERRUN %d\n", rt_whoami());
				}
			}				
			rt_sem_signal(mutex_pro_speed);
			rt_sem_signal(mutex_raw_speed);	
		}
	
	}

//TEMP
	static void function_temperature(int t)
	{
		if(temperature == 0){
			return;
		}
		while (1)
		{
			stop =0;
			//MBX_STOP_REQ
			rt_mbx_receive_if(mbx_stop_temp, &stop, sizeof(int));
			if(stop == ID_TEMP){
			//MUTEX SU PROCESS
				rt_sem_wait(mutex_pro_temp);
				p_processed_sensors_data->altitude = -1;
				rt_sem_signal(mutex_pro_temp);

				return;
			}

		// WAIT SU RAW E PRO
		rt_sem_wait(mutex_raw_temp);
		rt_sem_wait(mutex_pro_temp);

		//VALORE ERRATO
		for(i=0; i < TEMP_SIZE - 1; i++){
			if(p_raw_sensors_data->temperatures[i] == 0){
				int msg = ID_TEMP;
				rt_mbx_send(mbx_ape_req, &msg, sizeof(int));
			}	
		}

		//VALORE CORRETTO
		for(i=0; i<TEMP_SIZE; i++){
			if(p_raw_sensors_data->temperatures[i] != 0){
				//VALORE IN PROD				
				p_processed_sensors_data->temperature = p_raw_sensors_data->temperatures[i];
				//CALCOLO SLACK E OVERRUN
					slack = count2nano(next_period() - rt_get_time());
					if(slack<0){
					rt_printk("TASK ALTITUDE SLACK TIME ERROR %d\n", rt_whoami());
					}
					overrun = rt_task_wait_period();
					if(overrun == RTE_TMROVRN){
					rt_printk("TASK ALTITUDE OVERRUN %d\n", rt_whoami());}
				}
			}
		}
				rt_sem_signal(mutex_pro_temp);
				rt_sem_signal(mutex_raw_temp);		
	}

//INIZIALIZZAZIONE
	int init_module(void)
	{
	
	//INIT TASK
		rt_task_init_cpuid(&altitude_task, (void *)function_altitude, 1, STACK_SIZE, 0, 0, 0, 0);
		rt_task_init_cpuid(&speeds_task, (void *)function_speeds, 1, STACK_SIZE, 0, 0, 0, 0);
		rt_task_init_cpuid(&temperature_task, (void *)function_temperature, 1, STACK_SIZE, 0, 0, 0, 0);

	//MBX
		mbx_ape_req = rt_typed_named_mbx_init(MBX_APE_REQ, MBX_SIZE, FIFO_Q);
		mbx_stop_speed = rt_typed_named_mbx_init(MBX_STOP_REQ_SPEED, MBX_SIZE, FIFO_Q);
		mbx_stop_alt = rt_typed_named_mbx_init(MBX_STOP_REQ_ALT, MBX_SIZE, FIFO_Q);
		mbx_stop_temp = rt_typed_named_mbx_init(MBX_STOP_REQ_TEMP, MBX_SIZE, FIFO_Q);


	//INIT SHM
		p_processed_sensors_data = rtai_kmalloc(PROC_SEN_SHM, sizeof(struct processed_sensors_data));
		p_raw_sensors_data = rtai_kmalloc(RAW_SEN_SHM, sizeof(struct raw_sensors_data));

	//SEM
		mutex_raw_temp = rt_typed_named_sem_init(MUTEX_RAW_TEMP,1,BIN_SEM | FIFO_Q);
		mutex_raw_speed = rt_typed_named_sem_init(MUTEX_RAW_SPEED, 1, BIN_SEM | FIFO_Q);
		mutex_raw_alt = rt_typed_named_sem_init(MUTEX_RAW_ALT, 1, BIN_SEM | FIFO_Q);
		mutex_pro_temp = rt_typed_named_sem_init(MUTEX_PRO_TEMP,1,BIN_SEM | FIFO_Q);
		mutex_pro_speed = rt_typed_named_sem_init(MUTEX_PRO_SPEED, 1, BIN_SEM | FIFO_Q);
		mutex_pro_alt = rt_typed_named_sem_init(MUTEX_PRO_ALT, 1, BIN_SEM | FIFO_Q);

	//TODO: avvio task periodici
		tick_period = nano2count(TICK_TIME);	//Trasformo da nanosecondi a internal clock
		fase = rt_get_time() + 3 * tick_period; //Task sincroni

	//MAKE TASK
		rt_task_make_periodic(&altitude_task, fase, tick_period);
		rt_task_make_periodic(&speeds_task, fase, 2 * tick_period);
		rt_task_make_periodic(&temperature_task, fase, 4 * tick_period);

	//RM
		rt_spv_RMS(0);
		return 0;
	}

//CLEAN
	void cleanup_module(void)
	{
	//TODO: rimozione task e shared memory
	//rimozione task
		rt_task_delete(&altitude_task);
		rt_task_delete(&speeds_task);
		rt_task_delete(&temperature_task);

	//rimozione SEM
		rt_named_sem_delete(mutex_raw_temp);
		rt_named_sem_delete(mutex_raw_alt);
		rt_named_sem_delete(mutex_raw_speed);
		rt_named_sem_delete(mutex_pro_temp);
		rt_named_sem_delete(mutex_pro_alt);
		rt_named_sem_delete(mutex_pro_speed);


	//rimozione shared memory
		rtai_kfree(RAW_SEN_SHM);
		rtai_kfree(PROC_SEN_SHM);

	//RIMOZIONE MBX
	rt_named_mbx_delete(mbx_stop_alt);
	rt_named_mbx_delete(mbx_stop_speed);
	rt_named_mbx_delete(mbx_stop_temp);
	rt_named_mbx_delete(mbx_ape_req);
}
