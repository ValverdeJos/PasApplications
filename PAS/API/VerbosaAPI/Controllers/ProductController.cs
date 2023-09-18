using APIEntraiment.Class;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using VerbosaAPI.Class;
using VerbosaAPI.Context;

namespace VerbosaAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ProductController : ControllerBase
    {
        private readonly ProductDbContext _context;

        public ProductController(ProductDbContext context) => _context = context;

        [HttpGet("/Product")]
        public  async Task<IEnumerable<Product>>  Get()
        {
            //Logger.Log($"Get: {_context.Products.ToListAsync()}");
            return await  _context.Products.ToListAsync();
        }


        [HttpGet("/Product/{id}")]
        [ProducesResponseType(typeof(Product),StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status404NotFound)]
        public  async Task<IActionResult> GetById(int id)
        {

            var product = await _context.Products.FindAsync(id);
            Logger.Log($"Get: {product}");
            return product == null ? NotFound() : Ok(product);

        }


        [HttpGet("/UserDiscord/{nameUserDiscord}")]
        [ProducesResponseType(typeof(Product), StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status404NotFound)]
        public async Task<IActionResult> GetByIdUserDiscord(string nameUserDiscord)
        {

            var product = await _context.Products.FirstOrDefaultAsync(p => p.NameUserDiscord == nameUserDiscord);
            Logger.Log($"Get: {product}");
            return product == null ? NotFound() : Ok(product);

        }


        [HttpGet("/Compra/{NCompra}")]
        [ProducesResponseType(typeof(Product), StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status404NotFound)]
        public async Task<IActionResult> GetByIdCompra(string NCompra)
        {

            var product = await _context.Products.FirstOrDefaultAsync(p => p.IdCompra == NCompra);
            Logger.Log($"Get: {product}");
            return product == null ? NotFound() : Ok(product);

        }

        [HttpGet("/Serve/{ServeID}")]
        [ProducesResponseType(typeof(Product), StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status404NotFound)]
        public async Task<IActionResult> GetByServeID(string ServeID)
        {

            var product = await _context.Products.FirstOrDefaultAsync(p => p.IdServe == ServeID);
            Logger.Log($"Get: {product}");
            return product == null ? NotFound() : Ok(product);

        }


        [HttpPost("/ProductPost")]
        [ProducesResponseType(StatusCodes.Status201Created)]
        public async Task<IActionResult> Create(Product product)
        {
            if (string.IsNullOrEmpty(product.Description))
            {
                product.Description = "Produto Da Digiverse Alentejo";
            }

            await _context.Products.AddAsync(product);
            await _context.SaveChangesAsync();
            Logger.Log($"Create: {product}");
            return CreatedAtAction(nameof(GetById), new { id = product.Id }, product);

        }


        [HttpPut("/ProductPut/{productid}")]
        [ProducesResponseType(StatusCodes.Status204NoContent)]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        public async Task<IActionResult> Update(int productid, Product product)
        {
            if (productid != product.Id) return BadRequest();

            _context.Entry(product).State = EntityState.Modified;
            await _context.SaveChangesAsync();
            Logger.Log($"Update: {product}");
            return NoContent();
        }


        [HttpPut("/UserDiscordPut/{UserDiscord}")]
        [ProducesResponseType(StatusCodes.Status204NoContent)]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        public async Task<IActionResult> UpdateWithName(string UserDiscord, Product product)
        {
            if (UserDiscord != product.NameUserDiscord) return BadRequest();

            _context.Entry(product).State = EntityState.Modified;
            await _context.SaveChangesAsync();
            Logger.Log($"Update: {product}");
            return NoContent();
        }



        [HttpDelete("/ProductDelete/{id}")]
        [ProducesResponseType(StatusCodes.Status204NoContent)]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        public async Task<IActionResult> Delete(int id)
        {
            var productToDelete = await _context.Products.FindAsync(id);
            if (productToDelete == null) return NotFound();
            _context.Products.Remove(productToDelete);
            await _context.SaveChangesAsync();
            Logger.Log($"Delete: {productToDelete}");
            return NoContent();
        }



        [HttpDelete("/Compra/{NCompra}")]
        [ProducesResponseType(StatusCodes.Status204NoContent)]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        public async Task<IActionResult> DeleteByIdCompra(string NCompra)
        {
            var productToDelete = await _context.Products.FirstOrDefaultAsync(p => p.IdCompra == NCompra);
            if (productToDelete == null) return NotFound();
            _context.Products.Remove(productToDelete);
            await _context.SaveChangesAsync();
            Logger.Log($"Delete with Id Compra: {productToDelete}");
            return NoContent();
        }


        [HttpDelete("/Serve/{ServeID}")]
        [ProducesResponseType(StatusCodes.Status204NoContent)]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        public async Task<IActionResult> DeleteByIdServe(string ServeID)
        {
            var productToDelete = await _context.Products.FirstOrDefaultAsync(p => p.IdServe == ServeID);
            if (productToDelete == null) return NotFound();
            _context.Products.Remove(productToDelete);
            await _context.SaveChangesAsync();
            Logger.Log($"Delete with Id Compra: {productToDelete}");
            return NoContent();
        }

    }
}
