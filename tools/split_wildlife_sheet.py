"""
Split a 3x5 wildlife sprite sheet into per-animal PNGs and save to res/drawable-nodpi/
Usage:
  1. Put your sheet at: app/src/main/assets/wildlife_sheet.png
  2. From project root run: python tools/split_wildlife_sheet.py

The script assumes the sheet contains 3 rows x 5 columns in left-to-right, top-to-bottom order:
[tiger, lion, elephant, leopard, bear,
 gaur, wild_boar, crocodile, snake, wolf,
 deer, peacock, monkey, fox, eagle]

It saves files named: animal_tiger.png, animal_lion.png, ... into app/src/main/res/drawable-nodpi/
If drawable-nodpi doesn't exist it will create it.
"""

from PIL import Image
from pathlib import Path
import sys

ROOT = Path(__file__).resolve().parents[1]
SHEET_PATH = ROOT / "app" / "src" / "main" / "assets" / "wildlife_sheet.png"
OUT_DIR = ROOT / "app" / "src" / "main" / "res" / "drawable-nodpi"

# Mapping in sheet order
NAMES = [
    "animal_tiger",
    "animal_lion",
    "animal_elephant",
    "animal_leopard",
    "animal_bear",
    "animal_gaur",
    "animal_boar",
    "animal_crocodile",
    "animal_snake",
    "animal_wolf",
    "animal_deer",
    "animal_peacock",
    "animal_monkey",
    "animal_fox",
    "animal_eagle",
]

ROWS = 3
COLS = 5

def main():
    if not SHEET_PATH.exists():
        print(f"Sprite sheet not found at: {SHEET_PATH}")
        print("Please place your uploaded sprite sheet at that path and re-run this script.")
        sys.exit(1)

    OUT_DIR.mkdir(parents=True, exist_ok=True)

    img = Image.open(SHEET_PATH).convert("RGBA")
    w, h = img.size
    cell_w = w // COLS
    cell_h = h // ROWS

    print(f"Sheet size: {w}x{h}, cell: {cell_w}x{cell_h}")

    i = 0
    for r in range(ROWS):
        for c in range(COLS):
            if i >= len(NAMES):
                break
            left = c * cell_w
            upper = r * cell_h
            right = left + cell_w
            lower = upper + cell_h
            crop = img.crop((left, upper, right, lower))

            # Optional: trim transparent border (simple bounding box)
            bbox = crop.getbbox()
            if bbox:
                crop = crop.crop(bbox)

            out_path = OUT_DIR / f"{NAMES[i]}.png"
            crop.save(out_path)
            print(f"Wrote {out_path}")
            i += 1

    print("Done. Now rebuild the app: .\\gradlew.bat :app:assembleDebug")

if __name__ == '__main__':
    main()
