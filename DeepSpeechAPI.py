from deepspeech import Model
import scipy.io.wavfile as wav
from Recorder import record_audio

# https://progur.com/2018/02/how-to-use-mozilla-deepspeech-tutorial.html

# define the model and input paths
model_path = "data/DeepSpeech/models/output_graph.pb"
alphabet_path = "data/DeepSpeech/models/alphabet.txt"
lm_path = "data/DeepSpeech/models/lm.binary"
trie_path = "data/DeepSpeech/models/trie"

audio_path = "data/DeepSpeech/audio/test.wav"

# record audio with the onboard microphone
# audio_path = "data/DeepSpeech/audio/record.wav"
# record_audio(audio_path)

# load the model
ds = Model(model_path, 26, 9, alphabet_path, 500)
ds.enableDecoderWithLM(alphabet_path, lm_path, trie_path, 1, 0)

# process and display the input stream
fs, audio = wav.read(audio_path)
processed_data = ds.stt(audio, fs)
print(processed_data)
