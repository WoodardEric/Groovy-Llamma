using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;
using Groovy_Llamma.Services;
using Groovy_Llamma.ViewModels;

namespace Groovy_Llamma
{
    public partial class MainPage : ContentPage
    {
        public MainPage()
        {
            InitializeComponent();
            BindingContext = new AudioPlayerViewModel(DependencyService.Get<AudioPlayerService>());
        }
    }
}
