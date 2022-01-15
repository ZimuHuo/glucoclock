# Gluco'clock

Link to the app: https://glucoclock.herokuapp.com/

Demo video: https://www.youtube.com/watch?v=UiII07obTWA

_Tick, tock, Gluco'clock! Time for updating your diabetes logbook!_ 

This project offers a digital diabetic logbook solution in the form of a web application. By providing a simplistic user interface and essential functionalities backed up by cloud databases and security configurations, the app aims at improving diabetes patient experience, facilitating patient-doctor communications, as well as providing authorised researchers access to the anonymised database for scientific research.

## IMPORTANT NOTE
Please use Chrome to run the project or open the link.

- If you'd like to compile the source code:
  - Remember to change the spring.datasource.password in the `src/main/resources/application.properties` file to yours.
  - Components including charts and confirmation dialogs would require Vaadin pro licenses.

- If you'd like to use the app link directly:
  - Note that the blood glucose charts may not work on Heroku due to storage limitations imposed by the free plan.

## User roles
- Patient
- Doctor
- Researcher

## Functionalities
- Patients:
  - Keep a record of their diabetes type, insulin type and injection method
  - Upload simple/comprehensive/intensive logbook entries
  - View historical data
  - View plots of historical data
  - Fill in questionnaire to learn more about causes to symptoms

- Doctors:
  - Connect to patients and view their data
  - Receive alarms on patients' abnormal blood glucose level via email and on the app

- Researchers:
  - Download the anonymised database based on search requirements on age range, gender, diabetes type and insulin type

## Package organisation
- `views` package in `src/main/java` contains the frontend views of the application.
- `database` package in `src/main/java` contains MVC models of data to be stored in the database.
- `security` package in `src/main/java` contains the security implementations of the application.
- `test` package in `src` contains the unit testing code for entities.
- `themes` folder in `frontend/` contains the custom CSS styles.
