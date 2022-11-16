pipeline {
	agent any
	stages {
		stage('Checkout GIT') {
		 steps {
			echo 'Pulling...';
			 git branch: 'roua',
			 url : 'https://github.com/roua05/DevRoua.git',
			 credentialsId:'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';
		
			  }
		  }
		  stage('maven clean') {
		 steps {
			sh 'mvn -B -DskipTests clean'
			  }
		  }
		stage('maven package') {
		 steps {
			sh 'mvn package'
			  }
		  }
		stage('maven compile') {
		 steps {
			sh 'mvn compile'
			  }
		  }
		stage ('maven test') {
      		 steps {
       			 sh 'mvn test'
     			  }
  		  }
		  stage('SonarQube') {
		 steps {
			 withSonarQubeEnv('sonarqube-8.9.7'){
			sh 'mvn sonar:sonar'
			 }
			
			  }
		  }
		stage('Nexus') {
		 steps {
			sh 'mvn deploy -DskipTests'
			  }
			  }
			
			 
}
}