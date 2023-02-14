//---------------- PARAMETERS.H ----------------------- 

#define TICK_TIME 250000000

#define STACK_SIZE 10000

#define ALTITUDE_SIZE 5
#define SPEED_SIZE 3
#define TEMP_SIZE 3

#define RAW_SEN_SHM 121111
#define PROC_SEN_SHM 121112

#define MUTEX_RAW_ALT "TS1"
#define MUTEX_RAW_SPEED "SS1"
#define MUTEX_RAW_TEMP "AS1"

#define MUTEX_PRO_ALT "TS2"
#define MUTEX_PRO_SPEED "SS2"
#define MUTEX_PRO_TEMP "AS2"

#define MBX_APE_REQ "APE_REQ"
#define MBX_SIZE 256

#define MBX_STOP_REQ_TEMP "TEMP_STOP"
#define MBX_STOP_REQ_ALT "ALT_STOP"
#define MBX_STOP_REQ_SPEED "SPE_STOP"

#define ID_TEMP 1
#define ID_SPEED 2
#define ID_ALT 3

struct raw_sensors_data
{
    unsigned int altitudes[ALTITUDE_SIZE]; 	//uptated every 250ms
    unsigned int speeds[SPEED_SIZE];		//updated every 500ms
    int temperatures[TEMP_SIZE];		//uptaded every	second
};


struct processed_sensors_data
{
    unsigned int altitude;
    unsigned int speed;
    int temperature;
};