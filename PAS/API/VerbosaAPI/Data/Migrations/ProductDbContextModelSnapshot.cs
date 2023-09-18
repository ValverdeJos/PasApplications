﻿// <auto-generated />
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using VerbosaAPI.Context;

#nullable disable

namespace VerbosaAPI.Data.Migrations
{
    [DbContext(typeof(ProductDbContext))]
    partial class ProductDbContextModelSnapshot : ModelSnapshot
    {
        protected override void BuildModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("ProductVersion", "7.0.9")
                .HasAnnotation("Relational:MaxIdentifierLength", 64);

            modelBuilder.Entity("VerbosaAPI.Class.Product", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    b.Property<string>("Created")
                        .IsRequired()
                        .HasColumnType("longtext");

                    b.Property<string>("Description")
                        .HasColumnType("longtext");

                    b.Property<string>("IdCompra")
                        .IsRequired()
                        .HasColumnType("longtext");

                    b.Property<string>("IdServe")
                        .IsRequired()
                        .HasColumnType("longtext");

                    b.Property<string>("IdUserServe")
                        .IsRequired()
                        .HasColumnType("longtext");

                    b.Property<int>("MesPremium")
                        .HasColumnType("int");

                    b.Property<int>("NameProduct")
                        .HasColumnType("int");

                    b.Property<string>("NameServe")
                        .HasColumnType("longtext");

                    b.Property<string>("NameUserDiscord")
                        .HasColumnType("longtext");

                    b.Property<int>("Priority")
                        .HasColumnType("int");

                    b.HasKey("Id");

                    b.ToTable("Products");
                });
#pragma warning restore 612, 618
        }
    }
}
