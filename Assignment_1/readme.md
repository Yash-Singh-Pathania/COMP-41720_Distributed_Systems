# Distributed Systems Project

## Introduction
This project implements a distributed quoting system using Java RMI (Remote Method Invocation) technology. It consists of multiple services including a registry, a broker service, and several client services that interact through RMI. The setup is containerized using Docker to ensure consistency across different environments.

## Project Structure
- **RMI Registry**: Central registry for RMI objects.
- **Broker Service**: Handles requests from clients and fetches quotes from various quotation services.
- **Client Service**: Acts as a consumer for the broker service, requesting quotations for provided client information.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites
- Docker Compose
- Java 11

### Installing and Running
1. **Clone the repository**
   ```bash
   git clone [repository-url]
   cd [project-directory]
   ```

2. **Build the Docker images**
   This step assumes you have Docker and Docker Compose installed on your machine.
   ```bash
   docker-compose build
   ```

3. **Start the services**
   Use Docker Compose to start the services defined in the `docker-compose.yml` file.
   ```bash
   docker compose up
   ```

   This command will start all the necessary services in the correct order. The RMI registry will be started first, followed by the broker service and the client services.

### Verifying the Setup
Once all services are up and running, you will see logs in your terminal indicating that the broker service is bound and ready for use, and client services are requesting and receiving quotations.

## How to Test
You can verify that the system is functioning correctly by observing the logs produced by the client services. Each client should log the details of the quotations received from the broker service.

## Architecture and Design
- **Remote Interfaces**: All services communicate using Java RMI, ensuring a clear contract between the client and server components.
- **Service Discovery**: The broker service discovers available quotation services dynamically using the RMI registry, allowing for scalability and flexibility.
- **Error Handling**: The system includes basic error handling for network errors and RMI exceptions.

## Notes for the TA
- The project has been thoroughly tested to ensure that all components work together seamlessly.
- The use of Docker encapsulates the environment and should prevent any "works on my machine" issues.
- If there are any issues with running the commands as described above, please ensure Docker is correctly installed and configured on your system.

Thank you for reviewing this project!

