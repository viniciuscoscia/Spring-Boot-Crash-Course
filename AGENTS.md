# Repository Guidelines

## Project Structure & Module Organization

This is a Kotlin Spring Boot project using Gradle Kotlin DSL.

- `src/main/kotlin/com/viniciuscoscia/spring_boot_crash_course/`: application source code.
- `src/main/resources/`: runtime configuration, including `application.properties`.
- `src/test/kotlin/com/viniciuscoscia/spring_boot_crash_course/`: JUnit 5 and Spring Boot tests.
- `build.gradle.kts`: dependencies, Kotlin compiler options, Java toolchain, and tests.
- `settings.gradle.kts`: Gradle project name and plugin repositories.

Keep production code under the existing base package unless adding a clear feature package such as `controller`, `service`, `repository`, `model`, or `config`.

## Build, Test, and Development Commands

- `./gradlew build`: compiles the project, runs tests, and packages the application.
- `./gradlew test`: runs the JUnit Platform test suite.
- `./gradlew bootRun`: starts the Spring Boot application locally.
- `./gradlew clean`: removes generated build outputs.

The project uses Java 21 via Gradle toolchains. Spring snapshot repositories are configured, so dependency resolution may need network access.

## Coding Style & Naming Conventions

- Use idiomatic Kotlin with 4-space indentation in new files.
- Prefer constructor injection for Spring components.
- Keep classes focused; avoid abstractions before there is a clear need.
- Name Spring classes by role, for example `UserController`, `UserService`, `MongoUserRepository`, and `SecurityConfig`.
- Use explicit domain names instead of generic names like `Manager`, `Helper`, or `Util`.
- Keep configuration in `application.properties`; do not hardcode secrets or environment-specific values.

## Testing Guidelines

Tests use JUnit 5, Kotlin test, Spring Boot Test, Reactor Test, and Spring Security Test.

- Place tests in `src/test/kotlin` mirroring the production package.
- Name test classes with the `*Tests` suffix, matching the current project style.
- Prefer focused unit tests for business logic. Use `@SpringBootTest` only when Spring wiring is being verified.
- Run `./gradlew test` before opening a pull request.

## Commit & Pull Request Guidelines

Git history only shows `Initial commit`, so there is no established convention yet. Use short, imperative messages, for example `Add user registration endpoint`.

Pull requests should include:

- A brief description and motivation.
- Commands run for verification, especially `./gradlew test`.
- Linked issue or task when applicable.
- API examples or screenshots only when user-facing behavior changes.

## Security & Configuration Tips

This project includes Spring Security, MongoDB, validation, and JWT dependencies. Treat authentication, authorization, input validation, password handling, and token signing as security-sensitive. Keep secrets in environment variables or ignored local config, never in committed files.
