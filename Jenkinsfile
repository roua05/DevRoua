pipeline {
	agent any

       tools {
        maven 'maven'
    }

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
			sh " mvn sonar:sonar -Dsonar.projectKey=token -Dsonar.host.url=http://192.168.100.189:9000   -Dsonar.login=d26c73909cb4f24d583419d1650c3dfaf5d7affd"
			
			
			  }
		  }
		stage('Nexus') {
		         steps {
                script {
                configFileProvider([configFile(fileId: 'deploymentRepo', variable: 'setting')]) {
                    sh 'mvn  -B -DskipTests deploy -s $setting'

}                }
			  }
}			
			 

}
