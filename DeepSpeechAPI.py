from deepspeech import Model
import scipy.io.wavfile as wav

# https://progur.com/2018/02/how-to-use-mozilla-deepspeech-tutorial.html

# define the model and input paths
model_path = "C:/Users/Patri/Downloads/DeepSpeech/models/output_graph.pb"
alphabet_path = "C:/Users/Patri/Downloads/DeepSpeech/models/alphabet.txt"
audio_path = "C:/Users/Patri/Downloads/DeepSpeech/audio/4507-16021-0012.wav"

# load the model
ds = Model(model_path, 26, 9, alphabet_path, 500)

# process and display the input stream
fs, audio = wav.read(audio_path)
processed_data = ds.stt(audio, fs)
print(processed_data)
