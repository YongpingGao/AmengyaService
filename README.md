# Amengya Service

## Server IP: 
- 50.62.166.22

## mysql:
- UserName: entplanner
- Password: test
- Database Used: gaodweets
- Tables:
    1. dweets
    2. alarms
    3. commands
    
```sql
show databases;

CREATE DATABASE gaodweets;

USE gaodweets;

CREATE table dweets(
  D_ID int NOT NULL AUTO_INCREMENT,
  ThingName varchar(255) NOT NULL,
  TimeStamp varchar(255),
  Data varchar(255),
  PRIMARY KEY (D_ID)
);

CREATE table alarms(
  A_ID int NOT NULL AUTO_INCREMENT,
  ThingName varchar(255) NOT NULL,
  TimeStamp varchar(255),
  Data varchar(255),
  Status varchar(255),
  PRIMARY KEY (A_ID)
);

CREATE table commands(
  C_ID int NOT NULL AUTO_INCREMENT,
  ThingName varchar(255) NOT NULL,
  TimeStamp varchar(255),
  Data varchar(255),
  Status varchar(255),
  PRIMARY KEY (C_ID)
);
``` 

## Protocol
1. Add New Dweets:
```javascript
http://50.62.166.22:8080/AmengyaService/dweet/for/amengya?Temperature=20
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

2. Get the latest dweet:
```javascript
http://50.62.166.22:8080/AmengyaService/get/latest/dweet/for/amengya
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

3. Alarm Recieved
```javascript
http://50.62.166.22:8080/AmengyaService/alarm/Amengya/on?co=150
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

4. Command Recieved
```javascript
http://50.62.166.22:8080/AmengyaService/command/Amengya?Fan=on
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

## About GCM:
1. When server get the alarm from senser:
"http://50.62.166.22:8080/AmengyaService/alarm/Amengya/on?co=150"<br>
The server will automatically send alarm to the mobile(Android device)

2. API KEY:
    - AIzaSyD7NLBsfJ0r9vB3bbRpZR1uGXkhf4tSd48
    
3. Device Token (Nexus 4 Android):
    - fzjb7JXd7kk:APA91bGn_n_CNx_zZUkv1Dz_gjrFJy265S54n_DDfU82J2zfdMBIs-3GNCx4oQmixiob24zw0IoKxG8DI5EloUCZlzgvg8gJdXFfpCMZAIYP_T69P66oX0Mzx23jubxIcpU6DPRHLgsm

