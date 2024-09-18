# calamp_parse

This project was used to set up a service on Linux that opens a port to receive data packets from Calamp radars. The packets are interpreted and stored in a database for further use. It uses port 9094 and communicates via UDP. The implementation was carried out on a CentOS server, successfully extracting the packets.

## Features

- **CapitalizeServer:** A server that accepts client connections to convert text strings to uppercase.
- **DatClient:** A client that connects to the server and sends data.
- **DateServer:** A server that sends the current date to connected clients.

## Prerequisites

- **Java Development Kit (JDK):** Required to compile and run the Java code.
- **Apache Ant:** To compile the project using the `build.xml` file.
- **Operating System:** Designed to work on Linux-based systems, such as CentOS.

## Installation

1. Clone this repository.
2. Make sure you have JDK and Apache Ant installed.
3. Compile the project with Apache Ant using the following command:
    ```bash
    ant compile
    ```
4. To run the server:
    ```bash
    ant run
    ```

## Usage

- **CapitalizeServer:** 
  - Runs the server and waits for client connections.
  - Converts text strings sent by clients to uppercase.

- **DatClient:** 
  - Connects to the server and sends data for processing.

- **DateServer:**
  - Sends the current date to connected clients.

## License

This project does not specify a license.

## Authors

- Abraham SA
