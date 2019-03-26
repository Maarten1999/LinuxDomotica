#!/usr/bin/env python3

from time import sleep
import RPi.GPIO as GPIO
from gpiozero import LED
import sys


PIN = 21
PIN_1 = 21
PIN_2 = 20
PIN_3 = 16
PIN_4 = 12

def led_blink():
    while True:
        print("Led on")
        GPIO.output(PIN, GPIO.HIGH)
        sleep(1)
        print("Led off")
        GPIO.output(PIN, GPIO.LOW)        
        sleep(1)
    return None
    
def led_on(id):
    
    if id == 1:
        GPIO.output(PIN_1, GPIO.HIGH)
    elif id == 2:
        GPIO.output(PIN_2, GPIO.HIGH)
    elif id == 3:
        GPIO.output(PIN_3, GPIO.HIGH)
    elif id == 4:
        GPIO.output(PIN_4, GPIO.HIGH)
    return None
    
def led_off(id):
    if id == 1:
        GPIO.output(PIN_1, GPIO.LOW)
    elif id == 2:
        GPIO.output(PIN_2, GPIO.LOW)
    elif id == 3:
        GPIO.output(PIN_3, GPIO.LOW)
    elif id == 4:
        GPIO.output(PIN_4, GPIO.LOW)
    return None

def main():
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(PIN_1, GPIO.OUT)
    #GPIO.setup(PIN_1, GPIO.OUT)
    #GPIO.setup(PIN_1, GPIO.OUT)
    #GPIO.setup(PIN_1, GPIO.OUT)
    
    arg1 = sys.argv[1]
    arg2 = sys.argv[2]
    
    if arg1 == 0:
        led_off(arg2)
    elif arg1 == 1:
        led_on(arg2) 
    
    return None

if __name__ == "__main__":
    main()
