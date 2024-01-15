document.addEventListener('DOMContentLoaded', function() {
    // Project Squares
    var projectSquaresContainer = document.getElementById('projectSquares');

    // Example data for titles, descriptions, and links
    var projectData = [
        { title: 'Chess Game', description: 'Play chess with a friend or a bot powerd using AI this project is my best one yet', link: 'https://github.com/HaiderMalikk/Projects/tree/main/Chess', image: 'Chess.png' },
        { title: 'Email app', description: 'Send emails and files using this email app (email automation coming soon..)', link: 'https://github.com/HaiderMalikk/Projects/tree/main/Email_BOT', image: 'emailbot.png' },
        { title: 'Neural Network', description: 'This neural network can recognize hand drawn images of numbers', link: 'https://github.com/HaiderMalikk/Projects/tree/main/Neural%20Network%20For%20Digit%20Recognition', image: 'NN.png' },
        { title: 'Ping Pong game', description: 'This 2 player ping pong game is loads of fun and have many customization options', link: 'https://github.com/HaiderMalikk/Projects/tree/main/Ping%20Pong', image: 'ping pong.png' },
        { title: 'Mini Bank Account', description: 'allows you to understand OOP through a mini bank acount you can play around with', link: 'https://github.com/HaiderMalikk/Projects/tree/main/Dummy%20Bank%20Account', image: 'Bank.png' },
        { title: 'Project 6', description: 'Description for Project 6', link: 'https://github.com/HaiderMalikk/Projects/tree/main/Plant%20Watering%20System%20ARDUINO', image: 'plantwater.png' }
    ];

    for (var i = 0; i < projectData.length; i++) {
        var projectSquare = document.createElement('a');
        projectSquare.href = projectData[i].link;
        projectSquare.className = 'project-square';
        projectSquare.style.backgroundImage = "url('" + projectData[i].image + "')";

        // Create square container for background image
        var squareContent = document.createElement('div');
        squareContent.className = 'square-content';

        // Create title element above the square
        var titleElement = document.createElement('div');
        titleElement.className = 'title';
        titleElement.textContent = projectData[i].title;

        // Create description element below the square
        var descriptionElement = document.createElement('div');
        descriptionElement.className = 'description';
        descriptionElement.textContent = projectData[i].description;

        // Append title to squareContent
        squareContent.appendChild(titleElement);

        // Append squareContent to projectSquare
        projectSquare.appendChild(squareContent);

        // Append description to projectSquare
        projectSquare.appendChild(descriptionElement);

        projectSquaresContainer.appendChild(projectSquare);
    }



 

 
});
