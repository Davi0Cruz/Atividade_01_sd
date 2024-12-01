tcp:
	cd server; \
	sbt compile
	cd client; \
	sbt compile
	cd server; \
	sbt run & 
	sleep 5
	for p in 5100 5101 5102 5103 5104 5200 5201 5202 5203 5204; do \
		sudo tshark -i lo -T fields -e tcp.port -e frame.len -Y "tcp.port == $$p" > $$p.txt & \
	done
	sleep 3
	cd client; \
	sbt run
	sudo pkill tshark
	sleep 2

compile:
	cd server; \
	sbt compile
	cd client; \
	sbt compile

resume:
	touch result.txt
	rm result.txt
	for p in 5100 5101 5102 5103 5104 5200 5201 5202 5203 5204; do \
		awk -v p="$$p" '{sum +=$$NF}END{print p, sum }' $$p.txt >> result.txt; \
	done
