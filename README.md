# EnglishToJavaTranslator
WPI CS 534 -- Introduction to Artificial Intelligence


Project Description 

There are many tools for translating a human language to a human language such as Google Translate, Microsoft Translate, and Yandex to name a few. However, there are very few tools for translating a human language to a programming language. This type of capability would be especially helpful for people that suffer from Carpel Tunnel Syndrome (CTS) or some other ailment in the hands. Our project would aim to help fill this gap in technology. The idea of our project is to develop a tool that would translate English to compliable Java code. A user would be able to issue a simple command such as “add a double nested for loop on line 128” and we would translate this to English text, apply that to a trained model, and determine what it believes to be the most accurate Java representation of the English command. Given the time frame of this project, our team would focus on developing a proof-of-concept for this idea where we would be able to dictate very simple commands. We would also like to create a plugin for a popular integrated development environment (IDE) so developers can begin using this technology in the near future. 

This technology would enable developers to decrease dependence on their physical ability while focusing more on developing software. A 2006 study claimed that “higher risk for CTS was found with higher exposure to computer work” (Ali & Sathiyasekaran, 2006). Our project could have a meaningful impact on the long-term safety of professional developers. Furthermore, this technology could serve as the backbone to future developments in automated programming assistants, where one day we could be asking our personal programming assistant to “Develop a program to handle customer tracking, should use Java and ASP.NET. Program should also manage inventory and billing. Please have it done by the day after tomorrow”.

## Instructions

### Install PyAudio for Windows 10

You need to download pipwin at first:
```
pip install pipwin
```
And then install pyaudio with pipwin:
```
pipwin install pyaudio
```

### Get DeepSpeech running

1. Clone the repo
2. Download the models folder from OneDrive and move it into the DeepSpeech folder
3. - With audio_path you can select the path to an existing .wav file
   - With the method record_audio() you can record and process a sound with your onboard microphone
