# TechPulse
Dynamic RBAC Authorization Engine using Spring Boot

**Authorization Flow**
The authorization flow works as follows:
  1. A user authenticates using Basic Authentication
  2. Spring Security intercepts incoming HTTP requests
  3. Controller methods are protected using @PreAuthorize
  4. The hasPermission() expression triggers a CustomPermissionEvaluator
  5. The evaluator:
    1. Fetches user roles from the database
    2. Fetches permissions assigned to those roles
    3. Validates the required permission
  6. Access is granted or denied dynamically
No role names are checked directly in code.

**Permission Evaluation Logic**
Permission evaluation is performed dynamically using the following logic:
  1. A user can have multiple roles
  2. Each role can have multiple permissions
  3. Permissions are stored and managed in the database
  4. Authorization checks are based on permission names, not roles

The evaluation steps:
  1. Identify the authenticated usernam
  2. Load all roles mapped to the user
  3. Load all permissions mapped to those roles
  4. Compare required permission with available permissions

If a match exists → access allowed
Otherwise → access denied (403 Forbidden)

**How PermissionEvaluator Is Used**
A custom class implements Spring Security’s PermissionEvaluator interface.
This evaluator is registered using a MethodSecurityExpressionHandler, ensuring Spring Security uses it for all hasPermission() checks.
Example usage in controller:
@PreAuthorize("hasPermission(null, 'CREATE_ROLE')")
@PostMapping("/roles")
public Role createRole(@RequestBody RoleRequest request) {
    return roleService.createRole(request.getName());
}

This ensures:
  1. Centralized authorization logic
  2. No dependency on role names
  3. Runtime permission evaluation

**Why Hardcoded Roles Are Avoided**
Hardcoded role checks such as:
  hasRole("ADMIN")
  hasAuthority("ROLE_ADMIN")

are avoided because they:
  1. Are inflexible
  2. Require code changes for authorization updates
  3. Violate the open–closed principle

Advantages of this approach:
  1. Permissions can be added or removed without code changes
  2. Authorization rules can be updated at runtime
  3. Roles act only as permission containers
  4. System is scalable and maintainable

**Example Permission Checks**
API Endpoint	                                            Required Permission
POST /roles	                                              CREATE_ROLE
POST /permissions	                                        CREATE_PERMISSION
POST /roles/{roleId}/permissions/{permissionId}	          ASSIGN_PERMISSION
POST /users/{userId}/roles/{roleId}	                      ASSIGN_ROLE
GET /secure-data	                                        READ_SECURE_DATA

**Steps to Run the Application**
Prerequisites
  1. Java 17+
  2. Maven
  3. IDE (Eclipse / IntelliJ)

Steps
  1. Clone the repository
  2. Import the project into your IDE
  3. Run the main class:
      DynamicRbacApplication.java
  4. Application runs on:
      http://localhost:8286
  5. H2 Console:
      http://localhost:8286/h2-console
  6. JDBC URL:
      jdbc:h2:mem:rbacdb

Default Users:
Username	  Password	  Role
admin	      admin	      ADMIN
user	      user	      USER

**Unit Testing & Code Coverage**
1. All APIs are covered using JUnit 5 and MockMvc
2. Both allowed and denied authorization scenarios are tested
3. Security and permission logic is validated
4. Code coverage is measured using JaCoCo
5. Overall test coverage is greater than 80%, as required

Run tests:
  mvn test

Generate coverage report:
  target/site/jacoco/index.html
