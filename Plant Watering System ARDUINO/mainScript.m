% Auto Plant Watering System
clear all; close all

% Define Arduino pins
soilSensorPin = 'A3';        % Analog pin for soil moisture sensor
waterPumpPin = 'D3';         % Digital pin for water pump

% Define moisture thresholds
reallyDryValue = 1.45;        % Adjust as needed
moistureThreshold = 1.5;     % Adjust as needed
saturatedValue = 1.6;        % Adjust as needed

% Set the duration for running the system (in seconds)
runDuration = 3600; % Run for 1 hour i can change this as needed
wateringDuration = 3; % Duration to run the water pump (in seconds)

% Initialize Arduino connection
myBoard = arduino('COM3', 'Nano3');

% Initialize variables for storing data
timeValues = [];
voltageValues = [];

% Create a figure for the graph
figure;

% Get the start time
startTime = datetime('now');

% Main loop the datetime alows the graph to have a time axis while
% runduration allows me to run it for aslong as i want
while (datetime('now') - startTime) <= seconds(runDuration)
    % Call the function to update the plant watering system
    UpdatePlantWateringSystem(myBoard, soilSensorPin, waterPumpPin, reallyDryValue, moistureThreshold, saturatedValue);
    
    % Record time and voltage values
    currentTime = datetime('now');
    timeValues = [timeValues, currentTime];
    voltageValues = [voltageValues, readVoltage(myBoard, soilSensorPin)];

    % Plot the graph as a line graph
    plot(timeValues, voltageValues, 'LineWidth', 2);
    title('Soil Moisture Level over Time');
    xlabel('Time');
    ylabel('Voltage (V)');
    ylim([0 3]); % Set y-axis limits
    
    % Pause for a while before the next iteration
    pause(5); % the pause for 5 sec before checking soil mostiure again allows the water to set gving a more accurate reading therefore avoiding overwatering
    
    % Check if watering has been done, and if so, pause for a set duration
    if (currentTime - startTime) <= seconds(wateringDuration)
        disp('Watering in progress. Pausing...');
        pause(wateringDuration); % Pause for the watering duration
    end
end


disp('Program has completed.'); % once program ends
