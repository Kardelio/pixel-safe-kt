#make for the full run
#make justbuild for the second task
justbuild:
	@echo "Just Build"
	@./gradlew superBuild

encrypt:
	@echo "Running ENCRYPT: Code is 1234"
	@java -jar example/app-1.0.jar -m e -c 1234 -f example/secret.txt -o example/butt.png

decrypt:
	@echo "Running DECRYPT: Code is 1234"
	@java -jar example/app-1.0.jar -m d -c 1234 -f example/butt.png -o example/butt.png

