package org.koreait.edutainment;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import androidx.annotation.RequiresApi;

import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.polly.PollyClient;
import software.amazon.awssdk.services.polly.model.*;

public class StartActivity extends AppCompatActivity { //TTS
    private TextToSpeech tts;
    private Button btn_Speak;
    private EditText txtText;
    private PollyClient pollyClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        AwsBasicCredentials credentials = AwsBasicCredentials.create("AKIAT32FX7MATQZBOQNL", "EOSAMQtUFAQijcnIhuWkRtA3tHlML3hTUg2+ijBR");

        // Polly 클라이언트 초기화
        pollyClient = PollyClient.builder()
                .region(Region.US_EAST_1) // AWS 리전 설정
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!= TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.KOREAN);
                }
            }
        });

        btn_Speak = findViewById(R.id.btnSpeak);
        txtText = findViewById(R.id.txtText);

        btn_Speak.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                String text = txtText.getText().toString();
                // Polly로 텍스트를 음성으로 변환
                ResponseInputStream<SynthesizeSpeechResponse> response = pollyClient.synthesizeSpeech(SynthesizeSpeechRequest.builder()
                        .text(text)
                        .voiceId(VoiceId.SEOYEON)
                        .outputFormat(OutputFormat.MP3)
                        .build());

                // 변환된 음성 파일 재생
                byte[] audioBytes = null;
                try {
                    audioBytes = readAllBytes(response);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                playAudio(audioBytes);

            }
            //InputStream에서 바이트 배열로 변환하는 방법
            private byte[] readAllBytes(InputStream is) throws IOException {
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int nRead;
                byte[] data = new byte[1024];

                while ((nRead = is.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }

                buffer.flush();
                return buffer.toByteArray();
            }

            private void playAudio(byte[] audioBytes) {
                try {
                    // 바이트 배열로부터 FileDescriptor 생성
                    FileDescriptor fd = createTemporaryFileDescriptor(audioBytes);
                    // MediaPlayer를 사용하여 바이트 배열로부터 오디오 재생
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(fd);
                    mediaPlayer.prepare();
                    mediaPlayer.start();

                    // 재생이 끝나면 리소스 해제
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    // 오디오 재생 중 오류가 발생한 경우 처리
                }
            }

            private FileDescriptor createTemporaryFileDescriptor(byte[] audioBytes) throws IOException {
                // 임시 파일 생성
                File tempFile = File.createTempFile("tempAudio", null, getCacheDir());
                FileOutputStream fos = new FileOutputStream(tempFile);
                fos.write(audioBytes);
                fos.close();

                // 임시 파일의 디스크립터를 반환
                return new FileInputStream(tempFile).getFD();
            }

        });
    }

    @Override 
    public void onDestroy() {
        if (pollyClient != null) {
            pollyClient.close();
        }
        super.onDestroy();
    }
}