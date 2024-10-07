all: cls

cls:
	javac -sourcepath src -d classes src/*.java
	java -cp classes data.DataManagerMain data/testdata.manual.2009.06.14.csv


clean:
	rm -r classes 

