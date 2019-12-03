### Docker for developer Testing

# Run init  test
#./gradlew test
#Build  Image
./gradlew dockerBuildImage

#Create container 
./gradlew startContainer

#Run Container
docker run  vehicle-simulator