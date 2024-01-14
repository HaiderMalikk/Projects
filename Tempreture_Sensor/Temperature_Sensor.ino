#include <Arduino.h>
#include <U8x8lib.h>
#include <Wire.h>
#include <SparkFun_SGP30_Arduino_Library.h>
#include "DHT.h"

SGP30 mySensor; // Create an object of the SGP30 class
#define debug Serial

U8X8_SSD1306_128X64_NONAME_HW_I2C u8x8(/* reset=*/ U8X8_PIN_NONE);

const int potPin = A0;    // Analog pin for potentiometer
const int relayPin = 3;   // Digital pin for relay
const int ledPin = 4;     // Digital pin for LED
const int buzzerPin = 5;  // Digital pin for buzzer
#define DHTTYPE DHT20   // Change to the appropriate DHT type you are using
DHT dht(DHTTYPE);

void setup(void) {
    debug.begin(115200);
    debug.println("Temp, Humidity, and CO2 Display");

    Wire.begin();
    u8x8.begin();
    u8x8.setPowerSave(0);
    u8x8.setFlipMode(1);

    pinMode(buzzerPin, OUTPUT);
    pinMode(ledPin, OUTPUT);

    // Initialize the sensor
    if (mySensor.begin() == false) {
        debug.println("No SGP30 Detected. Check connections.");
        while (1);
    }

    // Initializes sensor for air quality readings
    // measureAirQuality should be called in one second increments after a call to initAirQuality
    mySensor.initAirQuality();
    dht.begin();
}

void loop(void) {
    // Read the value from the potentiometer
    int potValue = analogRead(potPin);

    // Map the potentiometer value to the range of the relay (0 to 1023 to 0 to 255)
    int relayValue = map(potValue, 0, 1023, 0, 255);

    // Control the relay based on the potentiometer value
    analogWrite(relayPin, relayValue);

    // Turn on LED at digital pin 4 when the fan is ON
    digitalWrite(ledPin, relayValue > 0 ? HIGH : LOW);

    // Measure CO2 level
    mySensor.measureAirQuality();

    // Read temperature and humidity
    float temp = dht.readTemperature();
    float humidity = dht.readHumidity();

    // Sound the buzzer if CO2 level is over 1000 ppm
    if (mySensor.CO2 > 1000) {
        // Use tone() to generate sound with a frequency based on the CO2 level
        int frequency = map(mySensor.CO2, 1000, 2000, 500, 2000); // Adjust the frequency range as needed
        tone(buzzerPin, frequency);
    } else {
        noTone(buzzerPin); // Stop the buzzer if CO2 level is below 1000 ppm
    }

    u8x8.setFont(u8x8_font_chroma48medium8_r);
    u8x8.clearDisplay(); // Clear the entire display

    // Display system status and voltage
    u8x8.setCursor(0, 0);
    u8x8.print("Fan: ");
    u8x8.print(relayValue > 0 ? "ON" : "OFF");
    u8x8.print("  V: ");
    u8x8.print(map(potValue, 0, 1023, 0, 10)); // Adjust the scaling factor as needed

    // Display temperature
    u8x8.setCursor(0, 2);
    u8x8.print("Temp: ");
    u8x8.print(temp);
    u8x8.print("C");

    // Display humidity
    u8x8.setCursor(0, 4);
    u8x8.print("Humidity: ");
    u8x8.print(humidity);
    u8x8.print("%");

    // Display CO2 level
    u8x8.setCursor(0, 6);
    u8x8.print("CO2: ");
    u8x8.print(mySensor.CO2);
    u8x8.print(" ppm");

    delay(500); // Adjust the delay according to your preference
}
