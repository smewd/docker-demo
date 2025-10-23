package javaforum.demo;


public record Data(
	String applicationName, 
	String environmentName, 
	String jdbcUrl, 
	String database, 
	String stringValue) {
}