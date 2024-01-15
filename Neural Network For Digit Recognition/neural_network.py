import os
import cv2 
import numpy as np
import matplotlib.pyplot as plt 
import tensorflow as tf

# mnist = tf.keras.datasets.mnist # get data for digits (labled)
# (x_train, y_train), (x_test, y_test) = mnist.load_data() # x is pixel data (the actual picture / pixles) and y is classification (number)
# # scalling a pixle from 0-250 (greyscale) to 0-1
# x_train = tf.keras.utils.normalize(x_train, axis=1)
# x_test = tf.keras.utils.normalize(x_test, axis=1)

# model = tf.keras.models.Sequential() # starts neural network
# # add first layer with an input shape of (28*28 pixels/image)
# model.add(tf.keras.layers.Flatten(input_shape=(28,28))) # makes a grid of 28x28 pixles into one flat like of pixles
# model.add(tf.keras.layers.Dense(128, activation="relu")) # dense layer is where the neorons are conetcted to other neurons
# model.add(tf.keras.layers.Dense(128, activation="relu"))
# model.add(tf.keras.layers.Dense(10, activation="softmax")) # out put layer 1-10 for the digit softmax is confidence of ai answer 0-1

# model.compile(optimizer='adam', loss='sparse_categorical_crossentropy', metrics=['accuracy'])

# # model.fit is the training function
# model.fit(x_train, y_train, epochs = 3) # epox is the amout of iteriation of how many times ai will see the data

# model.save('handwritten.model')

# after using the commented code to train our nural network now we just use our model as its trained 
model = tf.keras.models.load_model('handwritten.model')

image_number = 1
while os.path.isfile(f"digits/digit{image_number}.png"):
    try:
        img = cv2.imread(f"digits/digit{image_number}.png")[:,:,0] # dont care about color
        img = np.invert(np.array([img])) # make it white on black to read greyscale value
        prediction = model.predict(img)
        print(f"THE FOLLOWING NUMBER IS: {np.argmax(prediction)}") # sees which neuron has highest value
        plt.imshow(img[0], cmap=plt.cm.binary)
        plt.show()
    except:
        print("ERROR, Check image")
    finally:
        image_number += 1 #inc imagne 
        