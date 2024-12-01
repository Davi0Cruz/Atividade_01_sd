compile:

	javac src/low_data/ObjClient.java src/low_data/Item.java -d output/ObjClient
	javac src/low_data/ObjServer.java src/low_data/Item.java -d output/ObjServer
	javac src/low_data/CustomObjClient.java src/low_data/CustomItem.java -d output/CustomObjClient
	javac src/low_data/CustomObjServer.java src/low_data/CustomItem.java -d output/CustomObjServer
	javac src/low_data/JsonClient.java -d output/JsonClient
	javac src/low_data/JsonServer.java -d output/JsonServer
	javac src/low_data/XMLClient.java -d output/XMLClient
	javac src/low_data/XMLServer.java -d output/XMLServer
	protoc --java_out=src/ -I./src ./src/low_data/case_low_data.proto
	javac src/low_data/ProtoClient.java src/low_data/CaseLowData.java -d output/ProtoClient
	javac src/low_data/ProtoServer.java src/low_data/CaseLowData.java -d output/ProtoServer
run:
	java -classpath output/ObjServer low_data.ObjServer &
	sleep 2
	sudo tcpdump -i lo port 5100 > tcp.txt &
	sleep 2
	java -classpath output/ObjClient low_data.ObjClient
	sleep 5
	sudo pkill java
	sudo pkill tcpdump
	sleep 2
	awk '{sum+=$$NF}END{print "Obj", sum;}' tcp.txt > results/Obj.txt
