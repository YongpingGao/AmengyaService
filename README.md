# Amengya Service

## Introduction
- Amengya Service is a server application written in Java, deployed by tomcat.
- It is based on RESTful API (using JAX-RS) in [dweet](https://dweet.io) protocol.
- It serves communication between user's mobile phone and the IoT sensor.


## Protocol
-  Add New Dweets:
```javascript
http://XX.XX.XX.XX:8000/AmengyaService/dweet/for/amengya?Temperature=20
{
  "_this": "succeeded",
  "by": "dweeting",
  "the": "dweet",
  "with": {
    "thing": "amengya",
    "created": "2016-03-01 15:05:25.2",
    "content": {
      "Temperature": "20"
    }
  }
}
``` 

- Get the latest dweet:
```javascript
http://XX.XX.XX.XX:8000/AmengyaService/get/latest/dweet/for/amengya
{
  "_this": "succeeded",
  "by": "getting",
  "the": "dweets",
  "with": {
    "thing": "amengya",
    "created": "2016-03-01 15:05:25.2",
    "content": {
      "Temperature": "20"
    }
  }
}
``` 

- Alarm Recieved and Alarm send to mobile
```javascript
http://XX.XX.XX.XX:8000/AmengyaService/alarm/Amengya/on?co=150
{
  "_this": "succeeded",
  "by": "sending",
  "the": "alarm",
  "to": "myMobileA",
  "with": {
    "thing": "Amengya",
    "created": "2016-03-01 15:07:43.483",
    "status": "on",
    "content": {
      "co": "150"
    },
    "alarmID": 2
  }
}
``` 

- Command Recieved
```javascript
http://XX.XX.XX.XX:8000/AmengyaService/command/Amengya?Fan=on
{
  "_this": "succeeded",
  "by": "sending",
  "the": "command",
  "from": "myMobileA",
  "with": {
    "thing": "Amengya",
    "created": "2016-03-01 15:13:07.784",
    "status": "sent",
    "command": {
      "Fan": "on"
    },
    "commandID": 2
  }
}
``` 

- Command send
```javascript
http://XX.XX.XX.XX:8000/AmengyaService/dweet/for/amengya?Temperature=20
// Assume Command ID 8,9,10 are sent to the sensor side. 
// it will return:
{
  "_this": "succeeded",
  "by": "dweeting",
  "the": "dweet",
  "with": {
    "thing": "amengya",
    "created": "2016-03-02 00:40:57.622",
    "content": {
      "Temperature": "41"
    }
  },
  "commands": [
    {
      "c_id": 8,
      "thingName": "Amengya",
      "timeStamp": "2016-03-02 00:40:39.317",
      "data": {
        "Fan": "off",
        "Light": "off"
      },
      "status": "readytosend"
    },
    {
      "c_id": 9,
      "thingName": "Amengya",
      "timeStamp": "2016-03-02 00:40:46.927",
      "data": {
        "Fan": "on"
      },
      "status": "readytosend"
    },
    {
      "c_id": 10,
      "thingName": "Amengya",
      "timeStamp": "2016-03-02 00:40:54.015",
      "data": {
        "Lithg": "off"
      },
      "status": "readytosend"
    }
  ]
}
// When another "add dweet request" is sent to server, it will clean all the commands status from "readytosend" to "sent",
// which will return (the commands are changed to empty because all the commands have been sent to senser side):
{
  "_this": "succeeded",
  "by": "dweeting",
  "the": "dweet",
  "with": {
    "thing": "amengya",
    "created": "2016-03-02 00:44:46.438",
    "content": {
      "Temperature": "41"
    }
  },
  "commands": []
}
```



