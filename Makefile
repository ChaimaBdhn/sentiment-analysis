all: cls

cls:
	# Compilation 
	javac -sourcepath src -d bin src/algo/*.java src/data/*.java src/distance/*.java
# javac -d bin --module-path jars --add-modules javafx.controls,javafx.fxml src/ui/*.java

# CSV File cleaned
# java -cp bin data.DataManagerMain data/testdata.manual.2009.06.14.csv


# main:
# 	java -cp bin algo.KNNClassifierMain

clean:
	rm -r bin 

.PHONY: all clean