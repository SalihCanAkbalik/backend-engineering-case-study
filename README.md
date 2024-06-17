# Backend Engineering Case Study

Technologies Used Java & Spring Boot for developing the backend service. MySQL as the relational database for storing user and tournament data. Docker Compose to containerize the application and its dependencies, ensuring consistency across different environments. Maven for managing project dependencies and building the project

The TournamentServiceImpl class handles core business logic for creating tournaments, entering users into tournaments and processing rewards. This modular approach ensures the separation of concerns and makes the code more maintainable and testable. When creating tournament groups, we ensure that users are evenly distributed among groups and try to fill incomplete groups before creating new ones. This optimization helps in handling high user volume efficiently. Implementing a scheduled task to end tournaments at the end of the day and reset user statuses. This ensures the system is ready for the next days tournament without manual intervention.

Handling High Load Efficient Queries with using optimized queries to fetch users who can enter tournaments, reducing the load on the database. Asynchronous Processing in the enterTournament method, processing users asynchronously if the number of users entering the tournament is large. Connection Pooling leveraging connection pooling to efficiently manage database connections, improving performance under load.


![Screenshot_4](https://github.com/SalihCanAkbalik/backend-engineering-case-study/assets/138127376/fce2e759-f0a6-4c86-9c49-d886eb488db7)
