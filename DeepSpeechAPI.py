from deepspeech import Model
import scipy.io.wavfile as wav
from Recorder import record_audio

# https://progur.com/2018/02/how-to-use-mozilla-deepspeech-tutorial.html

# define the model and input paths
model_path = "data/DeepSpeech/models/output_graph.pb"
alphabet_path = "data/DeepSpeech/models/alphabet.txt"
audio_path = "data/DeepSpeech/audio/4507-16021-0012.wav"

audio_filename = "data/DeepSpeech/audio/test.wav"

# load
record_audio(audio_filename)

# load the model
ds = Model(model_path, 26, 9, alphabet_path, 500)

# process and display the input stream
fs, audio = wav.read(audio_filename)
processed_data = ds.stt(audio, fs)
print(processed_data)
