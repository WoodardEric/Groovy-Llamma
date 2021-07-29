using System;
using Android.Media;
using Groovy_Llamma.Droid.Services;
using Groovy_Llamma.Services;
using Xamarin.Forms;
using Xamarin.Essentials;


[assembly: Dependency(typeof(AudioPlayer))]
namespace Groovy_Llamma.Droid.Services
{
   public class AudioPlayer : AudioPlayerService
    {
        private MediaPlayer mediaPlayer;
        public Action OnFinishedPlaying { get; set; }
        public AudioPlayer() { }

        public void Play(string audioPath)
        {
            

            if (mediaPlayer != null)
            {
                mediaPlayer.Completion -= MediaPlayerCompletion;
                mediaPlayer.Stop();
            }    
            else
            {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.Prepared += (sender, args) =>
                {
                    mediaPlayer.Start();
                    mediaPlayer.Completion += MediaPlayerCompletion;
                };
            }
 
            mediaPlayer.Reset();
            mediaPlayer.SetVolume(1.0f, 1.0f);
            try 
            {
                mediaPlayer.SetDataSource(audioPath); 
            }
            catch(Exception e)
            {
                Console.WriteLine("Error: " + e.Message);
                return;
            }
            
            mediaPlayer.PrepareAsync();
            
            
        }

        void MediaPlayerCompletion(object sender, EventArgs e)
        {
            OnFinishedPlaying?.Invoke();
        }
        
        public void Pause()
        {
            mediaPlayer?.Pause();
        }

        public void Play()
        {
            mediaPlayer?.Start();
        }
    }
}