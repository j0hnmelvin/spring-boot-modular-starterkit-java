# Database Migrations

We are using [Flyway](https://flywaydb.org/documentation/usage/gradle/) an open-source tool for database migrations.

### Gradle Tasks:

#### flywayInfo

Prints the details and status information about all the migrations.

  ```
  ./gradlew flywayInfo -Pflyway.url=jdbc:postgresql://${psql_hostname}:5432/${psql_db_name} -Pflyway.user=${psql_user} -Pflyway.password=${psql_password} -Pflyway.schemas=${psql_schema}
  ```

#### flywayMigrate

Migrates the schema to the latest version. Flyway will create the schema history table automatically if it doesn't exist.

  ```
  ./gradlew flywayMigrate -Pflyway.url=jdbc:postgresql://${psql_hostname}:5432/${psql_db_name} -Pflyway.user=${psql_user} -Pflyway.password=${psql_password} -Pflyway.schemas=${psql_schema}
  ```

_NOTE_: For initial Flyway production deployments on projects with an existing schema, since we have set `baselineVersion` to `1.0`, `baselineDescription` to `Initial Version` and `baselineOnMigrate` to `true`, on executing the flywayMigrate gradle task the very first time:

    1. It will initialize the schema history table and
    2. The existing schema will then be baselined with the baselineVersion before executing the migrations and
    3. Only migrations above baselineVersion will then be applied (i.e., it will ignore the migration sql file with version `1.0` in the naming - V1.0__Base_Version.sql).


#### flywayClean

We have set `cleanDisabled` to `true` since flywayClean will drop all objects in the specified schema.


### Sample Usage:

```
% ./gradlew flywayInfo -Pflyway.url=jdbc:postgresql://localhost:5432/spring-boot-modular-starterkit-java -Pflyway.user=postgres -Pflyway.password=postgres -Pflyway.schemas=public

> Task :user-service:flywayInfo
Schema version: << Empty Schema >>
+-----------+---------+--------------+------+--------------+---------+----------+
| Category  | Version | Description  | Type | Installed On | State   | Undoable |
+-----------+---------+--------------+------+--------------+---------+----------+
| Versioned | 1.0     | Base Version | SQL  |              | Pending | No       |
+-----------+---------+--------------+------+--------------+---------+----------+

BUILD SUCCESSFUL in 1s
1 actionable task: 1 executed
```

```
% ./gradlew flywayMigrate -Pflyway.url=jdbc:postgresql://localhost:5432/spring-boot-modular-starterkit-java -Pflyway.user=postgres -Pflyway.password=postgres -Pflyway.schemas=public

BUILD SUCCESSFUL in 812ms
1 actionable task: 1 executed
```

```
% ./gradlew flywayInfo -Pflyway.url=jdbc:postgresql://localhost:5432/spring-boot-modular-starterkit-java -Pflyway.user=postgres -Pflyway.password=postgres -Pflyway.schemas=public   

> Task :user-service:flywayInfo
Schema version: 1.0
+-----------+---------+--------------+------+---------------------+---------+----------+
| Category  | Version | Description  | Type | Installed On        | State   | Undoable |
+-----------+---------+--------------+------+---------------------+---------+----------+
| Versioned | 1.0     | Base Version | SQL  | 2024-04-06 13:40:42 | Success | No       |
+-----------+---------+--------------+------+---------------------+---------+----------+

BUILD SUCCESSFUL in 464ms
1 actionable task: 1 executed
```
