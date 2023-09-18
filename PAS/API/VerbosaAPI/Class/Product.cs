using System.ComponentModel.DataAnnotations;

namespace VerbosaAPI.Class
{
    public class Product
    {


        [Required]
        public int Id { get; set; }

        public NameProduct NameProduct { get; set; }
        

        public string IdCompra { get; set; }

        public string? Description {
            get;
            set; 
        }

        public string Created { get; set; }

        [Required]
        public int MesPremium { get; set; }
        

        public string IdServe { get; set; }

        public string? NameServe { get; set; }

        public string IdUserServe { get; set; }

        public string? NameUserDiscord { get; set; }

        public Priority Priority { get; set; }

    }

    public enum NameProduct
    {
        Verbosa, WebDesign, App
    }

    public enum Priority
    {
        Low, Medium, High
    }
}
