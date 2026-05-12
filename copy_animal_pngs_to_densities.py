#!/usr/bin/env python3
import os
import shutil

res_path = "app/src/main/res"
drawable_path = os.path.join(res_path, "drawable")
densities = ["drawable-hdpi", "drawable-mdpi", "drawable-xhdpi", "drawable-ldpi"]

# Get all PNG animal files
animal_pngs = [f for f in os.listdir(drawable_path) if f.startswith("animal_") and f.endswith(".png")]

for density in densities:
    density_path = os.path.join(res_path, density)
    os.makedirs(density_path, exist_ok=True)
    
    for png_file in animal_pngs:
        src = os.path.join(drawable_path, png_file)
        dst = os.path.join(density_path, png_file)
        if os.path.exists(src) and not os.path.exists(dst):
            shutil.copy2(src, dst)
            print(f"✓ Copied {png_file} to {density}")

print(f"\n✓ All animal images distributed to {len(densities)} density folders")
