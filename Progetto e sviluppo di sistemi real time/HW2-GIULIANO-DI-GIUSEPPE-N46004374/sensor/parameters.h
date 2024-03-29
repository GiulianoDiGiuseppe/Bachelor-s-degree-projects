//---------------- PARAMETERS.H ----------------------- 
#define TICK_TIME 250000000

#define STACK_SIZE 10000

#define ALTITUDE_SIZE 5
#define SPEED_SIZE 3
#define TEMP_SIZE 3

#define RAW_SEN_SHM 121111
#define PROC_SEN_SHM 121112

#define SEM_TEMP "temp_mutex"
#define SEM_SPEED "speed_mutex"
#define SEM_ALT "alt_speed"

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
