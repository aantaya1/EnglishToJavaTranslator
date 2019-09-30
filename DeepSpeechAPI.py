from deepspeech import Model
import scipy.io.wavfile as wav
from Recorder import record_audio
import pyaudio

#p = pyaudio.PyAudio()
#for i in range(p.get_device_count()):
#    dev = p.get_device_info_by_index(i)
#    print((i, dev['name'], dev['maxInputChannels']))

soundObj = pyaudio.PyAudio()

# Learn what your OS+Hardware can do
defaultCapability = soundObj.get_default_host_api_info()
print(defaultCapability)

# See if you can make it do what you want
isSupported = soundObj.is_format_supported(input_format=pyaudio.paInt8, input_channels=1, rate=22050, input_device=0)
print(isSupported)

# https://progur.com/2018/02/how-to-use-mozilla-deepspeech-tutorial.html

# define the model and input paths
model_path = "C:/Users/Patri/Downloads/DeepSpeech/models/output_graph.pb"
alphabet_path = "C:/Users/Patri/Downloads/DeepSpeech/models/alphabet.txt"
audio_path = "C:/Users/Patri/Downloads/DeepSpeech/audio/4507-16021-0012.wav"

# load
audio = record_audio()

# load the model
ds = Model(model_path, 26, 9, alphabet_path, 500)

# process and display the input stream
fs, audio = wav.read(audio_path)
processed_data = ds.stt(audio, fs)
print(processed_data)
