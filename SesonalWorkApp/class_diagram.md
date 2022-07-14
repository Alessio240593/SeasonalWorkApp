classDiagram
direction BT
class Account {
  + Account() 
  - String username
  - String password
  + hashCode() int
   String password
   String username
}
class BirthData {
  + BirthData(Date, String, String) 
  - String birthplace
  - Date birthDate
  - String nationality
   String nationality
   Date birthDate
   String birthplace
}
class City {
<<enumeration>>
  + City() 
  + values() City[]
  + valueOf(String) City
}
class DaoEmployer {
<<Interface>>
  + addRecord(Record) void
  + login(String, String) boolean
  + search(String[]) List~Job~
  + updateRecord(Record) void
}
class DaoEmployerImplement {
  - DaoEmployerImplement() 
  - DaoEmployerImplement dao
  + addRecord(Record) void
  + search(String[]) List~Job~
  + gsonReader(String) List~Account~
  + updateRecord(Record) void
  + login(String, String) boolean
   DaoEmployerImplement dao
}
class Employer {
  + Employer(BirthData, Account, Record) 
  - int id
  - Account account
  - BirthData birthInfo
  + hashCode() int
  + equals(Object) boolean
   int id
   Account account
   BirthData birthInfo
}
class Esercizio4_fxml {
  + Esercizio4_fxml() 
  + start(Stage) void
  + main(String[]) void
  + initialize() void
  + handle2(ActionEvent) void
  + handle(ActionEvent) void
}
class FactoryModel {
  + FactoryModel() 
  + getWorker(String, String, List~Job~, Date, List~Language~, List~License~, boolean, List~City~, Season, Person, Record, int) Worker
}
class Home {
  + Home() 
  + main(String[]) void
  + start(Stage) void
}
class Job {
  + Job(Season, String, String, City, float, Jobs) 
  - String employerName
  - float grossDailySalary
  - String task
  - City area
  - Jobs job
  - Season period
   Season period
   String employerName
   City area
   Jobs job
   String task
   float grossDailySalary
}
class Jobs {
<<enumeration>>
  + Jobs() 
  + values() Jobs[]
  + valueOf(String) Jobs
}
class Language {
<<enumeration>>
  + Language() 
  + valueOf(String) Language
  + values() Language[]
}
class Launcher {
  + Launcher() 
  + main(String[]) void
}
class Launcher {
  + Launcher() 
  + setAge(int) int
  + gsonWriter(String, Gson, Launcher) void
  + main(String[]) void
  + gsonWriterList(String, Gson, List~Launcher~) void
  + gsonReader() void
}
class LauncherTest {
  + LauncherTest() 
  + launcherTest() void
}
class License {
<<enumeration>>
  + License() 
  + valueOf(String) License
  + values() License[]
}
class LoginController {
  + LoginController() 
   ActionEvent listener
   ActionEvent listener2
}
class Model {
  - Model() 
  + createEmployer(int, BirthData, Account, Record) Employer
  + createWorker(String, String, List~Job~, Date, List~Language~, List~License~, boolean, List~City~, Season, Person, Record, int) Worker
   Model model
}
class Person {
  + Person(Record) 
  - Record record
   Record record
}
class Record {
  + Record(String, String, String, String) 
  - String name
  - String surname
  - String cellnum
  - String email
  + equals(Object) boolean
  + hashCode() int
   String cellnum
   String surname
   String name
   String email
}
class Season {
<<enumeration>>
  + Season() 
  + values() Season[]
  + valueOf(String) Season
}
class SeasonalWorker {
  + SeasonalWorker(String, List~Job~, Date, List~Language~, List~License~, boolean, List~City~, Season, Person, Record, int) 
  - int seasonalWorkerId
   int seasonalWorkerId
}
class Worker {
  + Worker(String, List~Job~, Date, List~Language~, List~License~, boolean, List~City~, Season, Person, Record) 
  - Person emergencyContact
  - List~Job~ pastExperience
  - List~City~ activityArea
  - Date brithInfo
  - List~License~ liscense
  - List~Language~ languages
  - String address
  - boolean withVehicle
  - Season period
   Person emergencyContact
   Date brithInfo
   List~Job~ pastExperience
   List~City~ activityArea
   Season period
   String address
   List~License~ liscense
   List~Language~ languages
   boolean withVehicle
}

DaoEmployerImplement  ..>  DaoEmployer 
Employer "1" *--> "account 1" Account 
Employer "1" *--> "birthInfo 1" BirthData 
Employer  -->  Person 
FactoryModel  ..>  SeasonalWorker : «create»
Job "1" *--> "area 1" City 
Job "1" *--> "job 1" Jobs 
Job "1" *--> "period 1" Season 
LauncherTest  ..>  Launcher : «create»
Model  ..>  Employer : «create»
Person "1" *--> "record 1" Record 
SeasonalWorker  -->  Worker 
Worker "1" *--> "activityArea *" City 
Worker "1" *--> "pastExperience *" Job 
Worker "1" *--> "languages *" Language 
Worker "1" *--> "liscense *" License 
Worker  -->  Person 
Worker "1" *--> "emergencyContact 1" Person 
Worker "1" *--> "period 1" Season 
