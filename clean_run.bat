cd src\main\predictable-client
if "%1" == "prod" (
   call ng build --prod
)else (
	call ng build
)
cd ..\..\..
call mvn clean
call mvn install
call mvn spring-boot:run