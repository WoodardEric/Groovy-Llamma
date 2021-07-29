using System;
using System.Windows.Input;
using System.ComponentModel;
using Groovy_Llamma.Services;
using Xamarin.Forms;
using Xamarin.Essentials;

namespace Groovy_Llamma.ViewModels
{
	public class AudioPlayerViewModel : INotifyPropertyChanged
	{
		private AudioPlayerService audioPlayer;
		private bool isStopped;
		public event PropertyChangedEventHandler PropertyChanged;
		public AudioPlayerViewModel(AudioPlayerService audioPlayer)
		{
			this.audioPlayer = audioPlayer;
			this.audioPlayer.OnFinishedPlaying = () =>
			{
				isStopped = true;
				CommandText = "Play";
			};

			CommandText = "Play";
			isStopped = true;
		}

		private string _commandText;
		public string CommandText
		{
			get { return _commandText; }
			set
			{
				_commandText = value;
				PropertyChanged?.Invoke(this, new PropertyChangedEventArgs("CommandText"));
			}
		}
		private ICommand _playPauseCommand;
		public ICommand PlayPauseCommand
		{
			get
			{
				return _playPauseCommand ?? (_playPauseCommand = new Command(
                    async (obj) =>
					{
						if (CommandText == "Play")
						{
							if (isStopped)
							{
								isStopped = false;
								var file = await FilePicker.PickAsync();
								Console.WriteLine(file.FullPath);
				
												//var file = "Rat.mp3";

								audioPlayer.Play(file.FullPath);
							}
							else
							{
								audioPlayer.Play();
							}
							CommandText = "Pause";
						}
						else
						{
							audioPlayer.Pause();
							CommandText = "Play";
						}
					}));
			}
		}
	}
}