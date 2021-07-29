using System;

namespace Groovy_Llamma.Services
{
    public interface AudioPlayerService
    {
        void Play(string audioPath);
        void Play();
        void Pause();
        Action OnFinishedPlaying { get; set; }
    }
}

