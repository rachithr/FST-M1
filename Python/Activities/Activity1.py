from datetime import date
age=input("What is your age : ")
print("The year you will turn 100 is ", date.today().year - int(age) + 100)