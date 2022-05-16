# Archify


Assalam alaykoum , [Archify](https://github.com/OussamaNahnah/Archify)  made by  [Oussama Nahnah](https://www.linkedin.com/in/oussamanahnah/), is software that allows you to manage your archive, transmit and receive documents, and perform advanced searches.It allows you to export your data as an Excel spreadsheet.  

![alt text](https://github.com/OussamaNahnah/Archify/blob/main/Snapshots/screen1.png)
![alt text](https://github.com/OussamaNahnah/Archify/blob/main/Snapshots/screen2.png)
![alt text](https://github.com/OussamaNahnah/Archify/blob/main/Snapshots/screen3.png)
![alt text](https://github.com/OussamaNahnah/Archify/blob/main/Snapshots/screen4.png)

## requirements:
- [JDK 18]( https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html) 
- Exel

## installation setup:
-  install jdk 18 (required).
-  copy and extract **Archify zip** to a local destination (required).
-  install excel (recommended).
-  sqlite browser (advanced).

## architecture
```mermaid
graph LR
A[Archify container] --> B(Archify.jar)
A -->  C(Documents)
A -->  D(DataBase)
A -->  E(tempExcel.xls)
C -->  F(ENV folder)
C -->  G(REC folder)
D -->  k(db.db)
D -->  l(db_empty)
```
- Archif folder : is the container of program.
- Archify.jar : is the excutible program.
- Documents folder : contain to other folder ENV folder for sended document And REC folder for the receive document.
- DataBase folder : contain db.db file wich is the sqlite3 database and db-empty.db file wich is an empty database used when starting over.

## Warning:
- Any changes made to the program container **archify** will have an impact on the program.
- If you want to  use the program again, create a copy of db_empty.db then rename it to db.db

 
## ِcontact us:
- **email:** oussamanh7@gmail.com 
- **phone:** +213696900164
- **lindekin:** https://www.linkedin.com/in/oussamanahnah/
- **fb:** https://www.facebook.com/osma0k/
# أرشيفي
السلام عليكم ، تم إعداد برنامج Archify بواسطة أسامة نحناح لأرشيف المستندات المرسلة والمستقبلة ، مع خصائص مثل عمليات البحث المتقدمة وتصدير قائمة EXEL.

المتطلبات:
- جي دي كاي 18
- إكسيل

## إعداد التثبيت:
- تثبيت jdk 18 (مطلوب)
- انقل ملف البرنامج  **Archify zip** إلى وجهة محلية ثم فك الضغط (مطلوب)
- تثبيت اكسل (موصى به)
- متصفح sqlite (متقدم)

## هندسة معمارية
```mermaid
graph LR
A[Archify container] --> B(Archify.jar)
A -->  C(Documents)
A -->  D(DataBase)
A -->  E(tempExcel.xls)
C -->  F(ENV folder)
C -->  G(REC folder)
D -->  k(db.db)
D -->  l(db_empty)
```
- Archif folder :
 مجلد الأرشيف: هو حاوية البرنامج
- Archify.jar :
 هو البرنامج القابل للتنفيذ
- Documents folder : 
 يحتوي على مجلد آخر مجلد ENV للمستند المرسل ومجلد REC لمستند المستقبل
- DataBase folder :
 يحتوي على ملف db.db الذي يمثل قاعدة بيانات sqlite3 و ملف db-empty.db الذي يعد قاعدة بيانات فارغة تُستخدم عند البدء من جديد.
## تحذير:
- أي تعديل في حاوية البرنامج "archify" سيؤثر على البرنامج
- إذا كنت تريد استخدام البرنامج مرة أخرى ، فقم بإنشاء نسخة من db_empty.db ثم أعد تسميته إلى db.db


## ِتواصل معنا:
- **email:** oussamanh7@gmail.com 
- **phone:** +213696900164
- **lindekin:** https://www.linkedin.com/in/oussamanahnah/
- **fb:** https://www.facebook.com/osma0k/.


