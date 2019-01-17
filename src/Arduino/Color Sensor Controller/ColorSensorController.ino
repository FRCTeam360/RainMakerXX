#include <Wire.h>

#define MULTIPLEXERADDRESS 0x70

//Status LED pins
#define ledR 2
#define ledG 3
#define ledB 4

double sensor1[] = {0, 0, 0, 0};
double sensor2[] = {0, 0, 0, 0};
double sensor3[] = {0, 0, 0, 0};

void multiSelect(uint8_t i) {
    if (i > 7) return;
        Wire.beginTransmission(MULTIPLEXERADDRESS);
        Wire.write(1 << i);
        Wire.endTransmission();  
}

void initSensor() {
    for (int i = 0; i < 3; i++) {
        //Select Channel of Multiplexer
        multiSelect(i);
        //Enable sensor
        Wire.beginTransmission(0x39);
        Wire.write(0x80 | 0x0D);
        Wire.write(0x00);
        Wire.endTransmission();
        //Set wait time
        Wire.beginTransmission(0x39);
        Wire.write(0x80 | 0x0F);
        Wire.write(0x20);
        Wire.endTransmission();
        //Random Settings
        Wire.beginTransmission(0x39);
        Wire.write(0x80 | 0x01);
        Wire.write(0xAD);
        Wire.endTransmission();
        //ENABLE
        Wire.beginTransmission(0x39);
        Wire.write(0x80 | 0x00);
        Wire.write(0x03);
        Wire.endTransmission();
    }
}

void sensorLoop() {

    for (int i = 0; i < 3; i++) {

        multiSelect(i);

        double R, G, B, C, IR;
        byte rgbc[8];

        for (int i = 0; i < 8; i++) {
            Wire.beginTransmission(0x39);
            Wire.write(0x80 | 0x14 + i);
            Wire.endTransmission();
            Wire.requestFrom(0x39, 1);
            rgbc[i] = Wire.read();
        }

        R = 256 * rgbc[3] + rgbc[2];
        G = 256 * rgbc[5] + rgbc[4];
        B = 256 * rgbc[7] + rgbc[6];
        C = 256 * rgbc[1] + rgbc[0];

        // Calculate IR component
        IR = ((R + G + B - C) / 2);

        // Remove IR components
        R = R - IR;
        G = G - IR;
        B = B - IR;
        C = C - IR;

    }
}

void setup() {

    //Start serial and wire
    Serial.begin(9600);
    Wire.begin();

    //Attempt to connect to sensors
    

}

void loop() {



}