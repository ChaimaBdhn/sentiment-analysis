all: cls

cls:
	javac -sourcepath src -d classes src/*.java


clean:
	rm -r classes 

