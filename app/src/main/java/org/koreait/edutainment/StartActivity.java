package org.koreait.edutainment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/*
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import software.amazon.awssdk.services.polly.model.SynthesizeSpeechRequest;
import software.amazon.awssdk.services.polly.model.VoiceId;

/*
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.polly.AmazonPolly;
import com.amazonaws.services.polly.AmazonPollyClient;
import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest;
import com.amazonaws.services.polly.model.VoiceId;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
*/
public class StartActivity extends AppCompatActivity {
/*
    private TextToSpeech tts;
    private Button btn_Speak;
    private EditText txtText;
    private AmazonPolly pollyClient;
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }
/*
        // AWS Polly 클라이언트 초기화
        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIAT32FX7MATQZBOQNL",
                "EOSAMQtUFAQijcnIhuWkRtA3tHlML3hTUg2+ijBR");

        pollyClient = new AmazonPollyClient(credentials)
                .withRegion(Regions.ap-notrheast-2);

        // TextToSpeech 초기화
        tts = new TextToSpeech(this, status -> {
            if (status != TextToSpeech.ERROR) {
                tts.setLanguage(Locale.KOREAN);
            }
        });

        btn_Speak = findViewById(R.id.btnSpeak);
        txtText = findViewById(R.id.txtText);

        btn_Speak.setOnClickListener(v -> {
            String text = txtText.getText().toString();
            synthesizeAndPlay(text);
        });
    }

    private void synthesizeAndPlay(String text) {
        try (InputStream speechStream = pollyClient.synthesizeSpeech(
                new SynthesizeSpeechRequest()
                        .withText(text)
                        .withVoiceId(VoiceId.SEOYEON)
                        .withOutputFormat(OutputFormat.Mp3)
        ).getAudioStream()) {
            playAudioStream(speechStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void playAudioStream(InputStream audioStream) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(audioStream.getFD());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            mediaPlayer.setOnCompletionListener(mp -> {
                mp.release();
                try {
                    audioStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        if (pollyClient != null) {
            pollyClient.shutdown();
        }
        if (tts != null) {
            tts.shutdown();
        }
        super.onDestroy();
    }*/
}