all: cls

cls:
	# Compilation 
	javac -sourcepath src -d bin src/**/*.java

	# CSV File cleaned
	# java -cp bin data.DataManagerMain data/testdata.manual.2009.06.14.csv


clean:
	rm -r bin 

.PHONY: all clean