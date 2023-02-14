//-------GIULIANO-DI-GIUSEPPE-N46004374

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/mman.h>
#include <rtai_lxrt.h>
#include <rtai_mbx.h>
#include <rtai_shm.h>
#include <rtai_sem.h>
#include <signal.h>
#include <sys/io.h>
#include "headers.h"
#include <rtai_msg.h>
#include<pthread.h>

#define CPU 1

//EXIT
static int keep_on_running = 1;
static void endme(int dummy) {keep_on_running = 0;}

//TIME
static RTIME period,fase; 
static RTIME now , end1, end2=0;

static task_ape_info ape_inf;

//MBX
static MBX * mbx_stop_alt;
static MBX * mbx_stop_speed;
static MBX * mbx_stop_temp;
static MBX * mbx_ape_req;

//TASK
static RT_TASK *task_ape_alt;
static RT_TASK *task_ape_speed;
static RT_TASK *task_ape_temp;
static RT_TASK *task_monitor;
static RT_TASK *task_TBS;

//SEM
static SEM* mutex_raw_temp;
static SEM* mutex_raw_alt;
static SEM* mutex_raw_speed;
static SEM* mutex_pro_temp; 
static SEM* mutex_pro_alt;
static SEM* mutex_pro_speed;

//STRUCT
static struct raw_sensors_data r;
static struct processed_sensors_data pr;

static struct raw_sensors_data *raw;
static struct processed_sensors_data *processed;
static struct misure_lette *r_m;


//THREAD
static pthread_t thread_alt;
static pthread_t thread_speed;
static pthread_t thread_temp;
static pthread_t thread_TBS;

//Us, ds
static float Us = 1/2;
int deadline_prec = 0;

//Scelgo i seguenti Cs per i task aperiodici:  SpeedTask = 250ms, AltTask = 500ms, Temp_task = 250ms


static void* TBS_fun(void* p){
    int msg;

    if (!(task_TBS = rt_task_init_schmod(task_TBS, 1, 0, 0, SCHED_FIFO, CPU))) {
		printf("CANNOT INIT TBS TASK\n");
		exit(1);
	}

    fase = rt_get_time() + period;
    rt_task_make_periodic(task_TBS, fase, period);
    rt_make_hard_real_time();

// CICLO SULLA MBX
    while(keep_on_running){
	//MESSAGGIO RICEVUTO
        rt_mbx_receive(mbx_ape_req, &msg, sizeof(int));
		
		//CLASSIFICAZIONE MESSAGGIO
        if(msg == ID_TEMP){
            //TEMP
            rt_send(task_ape_temp, 1);
        }
        else if(msg == ID_SPEED){

            //SPEED
            rt_send(task_ape_speed, 1);
        }
        else if(msg == ID_ALT){

            //ALTITUDINE
            rt_send(task_ape_alt, 1);
        }

        rt_task_wait_period();
    }

    return 0; 
}

static void* temp_fun(void* p){
    int msg;
    int count = 0;
    int stop_req;

    if (!(task_ape_temp = rt_task_init_schmod(TEMP_TASK, 1, 0, 0, SCHED_FIFO, CPU))) {
		printf("CANNOT INIT TEMP APE TASK\n");
		exit(1);
	}

    rt_make_hard_real_time();

    while(keep_on_running){
        rt_receive(task_TBS, &msg);

        if(msg == 1){
            count++;

            //Setto la deadline
            now = rt_get_time();

            if(now > end2){
                end1 = now + nano2count(ape_inf.temp_Cs/(Us));
            }
            else{
                end1 = end2 + nano2count(ape_inf.temp_Cs/(Us));
            }
            end2 = end1;
        rt_task_set_resume_end_times(now, end1);

        //Preleva valori dalla shared memory in mutua esclusione
        rt_sem_wait(mutex_raw_temp);
        for(int i = 0; i < TEMP_SIZE; i++){
            r.temperatures[i] = raw->temperatures[i];
        }
        rt_sem_signal(mutex_raw_temp);

        rt_sem_wait(mutex_pro_temp);
        pr.temperature = processed->temperature;
        rt_sem_signal(mutex_pro_temp);

        if(count == 10){
            stop_req = 1;
            rt_mbx_send(mbx_stop_temp, &stop_req, sizeof(int));
        }
    }
    }

    return 0; 
}

static void* speed_fun(void* p){
    int msg;
    int count = 0;
    int stop_req;

    if (!(task_ape_speed = rt_task_init_schmod(SPEED_TSAK, 1, 0, 0, SCHED_FIFO, CPU))) {
		printf("CANNOT INIT SPEED APE TASK\n");
		exit(1);
	}

    rt_make_hard_real_time();

    while(keep_on_running){
        rt_receive(task_TBS, &msg);

        if(msg == 1){
            count++;
            //Setto la deadline
            now = rt_get_time();
            if(now > end2){
                end1 = now + nano2count(ape_inf.speed_Cs/(Us));
            }
            else{
                end1 = end2 + nano2count(ape_inf.speed_Cs/(Us));
            }
            end2 = end1;
        rt_task_set_resume_end_times(now, end1);
        
        //Preleva valori dalla shared memory in mutua esclusione
        rt_sem_wait(mutex_raw_speed);
        for(int i = 0; i < SPEED_SIZE; i++){
            r.speeds[i] = raw->speeds[i];
        }
        rt_sem_signal(mutex_raw_speed);

        rt_sem_wait(mutex_pro_speed);
        pr.speed = processed->speed;
        rt_sem_signal(mutex_pro_speed);

        if(count == 10){
            stop_req = 1;
            rt_mbx_send(mbx_stop_speed, &stop_req, sizeof(int));
        }

    }

    }

    return 0; 
}

static void* alt_fun(void* p){
    int msg;
    int count = 0;
    int stop_req;

    if (!(task_ape_alt = rt_task_init_schmod(ALT_TASK, 1, 0, 0, SCHED_FIFO, CPU))) {
	    printf("CANNOT INIT ALT APE TASK\n");
		exit(1);
	}

    rt_make_hard_real_time();

    while(keep_on_running){
        rt_receive(task_TBS, &msg);

        if(msg == 1){
            count++;
            //Setto la deadline
            now = rt_get_time();

            if(now > end2){
                end1 = now + nano2count(ape_inf.alt_Cs/(Us));
            }
            else{
                end1 = end2 + nano2count(ape_inf.alt_Cs/(Us));
            }

            end2 = end1;
        rt_task_set_resume_end_times(now, end1);
        
        //Preleva valori dalla shared memory in mutua esclusione
        rt_sem_wait(mutex_raw_alt);
        for(int i = 0; i < ALTITUDE_SIZE; i++){
            r.altitudes[i] = raw->altitudes[i];
        }
        rt_sem_signal(mutex_raw_alt);

        rt_sem_wait(mutex_pro_alt);
        pr.altitude = processed->altitude;
        rt_sem_signal(mutex_pro_alt);

        if(count == 20){
            stop_req = 1;
            rt_mbx_send(mbx_stop_alt, &stop_req, sizeof(int));
        }        

    }

    }

    return 0; 
}

int main(void){
	printf("The MONITOR is STARTED!\n");
	int data;
	signal(SIGINT, endme);

	if (!(task_monitor = rt_task_init_schmod(nam2num("MAINTSK OF MONITOR"),1,STACK_SIZE,sizeof(int), SCHED_FIFO, CPU))) {
			printf("CANNOT INIT MAIN TASK OF MONITOR\n");
			exit(1);
		}

	period = nano2count(TICK_PERIOD);

	ape_inf.alt_Cs = 2*Ci_TASK;
    ape_inf.speed_Cs = Ci_TASK;
    ape_inf.temp_Cs = Ci_TASK;
	
	//THREAD
		pthread_create(&thread_temp, NULL, temp_fun, NULL);
		pthread_create(&thread_speed, NULL, speed_fun, NULL);
		pthread_create(&thread_alt, NULL, alt_fun, NULL);
		pthread_create(&thread_TBS, NULL, TBS_fun, NULL);

	//MBX
		mbx_ape_req = rt_typed_named_mbx_init(MBX_APE_REQ, MBX_SIZE, FIFO_Q);
		mbx_stop_alt = rt_typed_named_mbx_init(MBX_STOP_REQ_ALT,MBX_SIZE,FIFO_Q);
		mbx_stop_temp = rt_typed_named_mbx_init(MBX_STOP_REQ_TEMP,MBX_SIZE,FIFO_Q);
		mbx_stop_speed = rt_typed_named_mbx_init(MBX_STOP_REQ_SPEED,MBX_SIZE,FIFO_Q);

	//MUTEX
		mutex_raw_alt = rt_typed_named_sem_init(MUTEX_RAW_ALT, 1, BIN_SEM | FIFO_Q);
		mutex_raw_speed = rt_typed_named_sem_init(MUTEX_RAW_SPEED, 1, BIN_SEM | FIFO_Q);
		mutex_raw_temp = rt_typed_named_sem_init(MUTEX_RAW_TEMP, 1, BIN_SEM | FIFO_Q);
		mutex_pro_alt = rt_typed_named_sem_init(MUTEX_PRO_ALT, 1, BIN_SEM | FIFO_Q);
		mutex_pro_speed = rt_typed_named_sem_init(MUTEX_PRO_SPEED, 1, BIN_SEM | FIFO_Q);
		mutex_pro_temp = rt_typed_named_sem_init(MUTEX_PRO_TEMP, 1, BIN_SEM | FIFO_Q);

	//SHM
		processed = (struct processed_sensors_data* ) rtai_malloc(PROC_SEN_SHM, sizeof(struct processed_sensors_data));
		raw = (struct raw_sensors_data*) rtai_malloc(RAW_SEN_SHM, sizeof(struct raw_sensors_data));
    	r_m = (struct misure_lette*) rtai_malloc(SHM_BUDDY, sizeof(struct misure_lette));


	//RIMOZIONE SEM
		rt_named_sem_delete(mutex_raw_alt);
		rt_named_sem_delete(mutex_raw_speed);
		rt_named_sem_delete(mutex_raw_temp);
		rt_named_sem_delete(mutex_pro_alt);
		rt_named_sem_delete(mutex_pro_speed);
		rt_named_sem_delete(mutex_pro_temp);


	//RIMOZIONE MBX
		rt_named_mbx_delete(mbx_ape_req);
		rt_named_mbx_delete(mbx_stop_speed);
		rt_named_mbx_delete(mbx_stop_alt);
		rt_named_mbx_delete(mbx_stop_temp);

	rt_task_delete(task_monitor);
	printf("TASK MONITOR STOPPED\n");

	return 0;
    
}




