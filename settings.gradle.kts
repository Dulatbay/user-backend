rootProject.name = "user-backend"
include("client-libs:keycloak")
findProject(":client-libs:keycloak")?.name = "keycloak"

