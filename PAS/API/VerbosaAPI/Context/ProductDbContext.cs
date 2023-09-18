using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Options;
using VerbosaAPI.Class;

namespace VerbosaAPI.Context
{
    public class ProductDbContext : DbContext
    {
        public ProductDbContext(DbContextOptions<ProductDbContext> options)
            : base(options)
        {

        }

        public DbSet<Product> Products { get; set; }
    }
}
