// vars/myLibrary.groovy

def buildApp() {
    echo "Now building the application..."
    // Mock build logic here
}

def deployApp(String branchName) {
    echo "Now deploying the application on branch: ${branchName}..."
    // Mock deploy logic here
}

def cleanup() {
    echo "Cleaning up after build and deployment..."
    // Mock cleanup logic here
}
def sonarCreateProject(String projectKey) {
        withSonarQubeEnv('SonarQubeScanner') {
            sh """
                curl -s -u ${env.SONAR_TOKEN}: \
               -X POST "${env.SONAR_HOST_URL}/api/projects/create" \
               -d "project=${projectKey}&name=${projectKey}"
            """
        }
}

def sonarLocalScan() {
    def scannerHome = tool 'SonarQubeScanner'
    withSonarQubeEnv('SonarQubeScanner') {
        sh """
            ${scannerHome}/bin/sonar-scanner \
            -Dsonar.projectKey=${env.JOB_NAME} \
            -Dsonar.projectName=${env.JOB_NAME} \
            -Dsonar.sources=. \
            -Dsonar.sourceEncoding=UTF-8
        """
    }
}
