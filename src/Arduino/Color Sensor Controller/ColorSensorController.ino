/*
ColorSensorController.ino

This file is intended to be used with 3 Rev Robotics Color sensors
Please do not change it unless you know 100% what you are doing or asking Gavin first!
It is very eazy to break something here and it is not java!

Version History:
1.0 Inital Release

*/

#include <Wire.h>

//I2C address values
#define MULTIPLEXERADDRESS 0x70
#define ControllerAddress 0x20

//Status LED pins
#define ledR 2
#define ledG 3
#define ledB 4

//Value for sensor C threshold
int threshold = 80;

//Program version
int version = 1.0;

//Stored Sensor Values
double sensor1[] = {0, 0, 0, 0};
double sensor2[] = {0, 0, 0, 0};
double sensor3[] = {0, 0, 0, 0};

void multiSelect(uint8_t i) {
    if (i > 7) return;
        Wire.beginTransmission(MULTIPLEXERADDRESS);
        Wire.write(1 << i);
        Wire.endTransmission();  
}

void initSensors() {
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

void startLEDS() {
    pinMode(ledR, OUTPUT);
    pinMode(ledG, OUTPUT);
    pinMode(ledB, OUTPUT);

    digitalWrite(ledR, HIGH);
    delay(100);
    digitalWrite(ledG, HIGH);
    delay(100);
    digitalWrite(ledB, HIGH);
    delay(100);

    digitalWrite(ledR, LOW);
    digitalWrite(ledG, LOW);
    digitalWrite(ledB, LOW);
}

void setLEDColor(char color, bool enable) {
    switch color {
        case R: 
            digitalWrite(ledR, enable);
            break;
        case G: 
            digitalWrite(ledG, enable);
            break;
        case B: 
            digitalWrite(ledB, enable);
            break;
    }
}

void sensorLoop() {

    for (int i = 0; i < 3; i++) {

        Serial.println("Initalizing Sensor " + i);
        multiSelect(i);

        double R, G, B, C, IR;
        byte rgbc[8];

        for (int j = 0; i < 8; i++) {
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

        //Set to non temp double values
        //This section is gross but is an easy way to do it
        if (i == 0) {
            sensor1 = {R, G, B, C};
        } else if (i == 1) {
            sensor2 = {R, G, B, C};
        } else {
            sensor3 = {R, G, B, C};
        }

    }

}

void onRioRequest() {

    Serial.println("roboRio requested data");

}

void setup() {

    //Start serial and wire
    Serial.begin(9600);
    Wire.begin(ControllerAddress);

    Serial.println("Color Sensor Controller : Version: " + version);
    Serial.println("Wire Started on address " + ControllerAddress);
    delay(100);
    Serial.println("Enabling Color Sensors");
    startLEDS();
    initSensors();

    //Respond to Rio requests
    Wire.onRequest(onRioRequest());

}

void loop() {

    sensorLoop();

}