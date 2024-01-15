% Function to update plant watering system
function UpdatePlantWateringSystem(myBoard, soilSensorPin, waterPumpPin, reallyDryValue, moistureThreshold, saturatedValue)

    % Read soil moisture level
    soilMoisture = readVoltage(myBoard, soilSensorPin);
    disp(['Current Soil Moisture: ', num2str(soilMoisture)]);

    % State machine logic
    if soilMoisture < reallyDryValue
        % Soil is dry, turn on water pump
        writeDigitalPin(myBoard, waterPumpPin, 1);
        disp('Soil is dry! Watering...');
        
        % Wait for 3 seconds
        pause(3);
        
        % Turn off water pump after 3 seconds
        writeDigitalPin(myBoard, waterPumpPin, 0);
        disp('Watering done.');
    elseif soilMoisture < moistureThreshold
        % Soil is moderately wet, turn on water pump
        writeDigitalPin(myBoard, waterPumpPin, 1);
        disp('Soil is moderately wet! Watering...');
        
        % Wait for 3 seconds
        pause(3);
        
        % Turn off water pump after 3 seconds
        writeDigitalPin(myBoard, waterPumpPin, 0);
        disp('Watering done.');
    else
        disp('Soil is wet enough. No need to water.');
    end

end
