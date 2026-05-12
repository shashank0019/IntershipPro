#!/usr/bin/env python3
"""
Generate AI-style animal images to replace artwork in MindMatrixpro
Creates illustrated animal images in the style of the app UI
"""

from PIL import Image, ImageDraw
import os

# Create output directory for different densities
output_dir = "app/src/main/res/drawable-xhdpi"
os.makedirs(output_dir, exist_ok=True)

def create_tiger_image():
    """Create stylized Tiger image"""
    img = Image.new('RGB', (256, 256), color='#FFB45C')
    draw = ImageDraw.Draw(img, 'RGBA')
    
    # Draw background gradient effect
    for i in range(256):
        shade = int(255 * (1 - i/256 * 0.4))
        draw.line([(0, i), (256, i)], fill=(shade, int(shade*0.7), int(shade*0.3)))
    
    # Draw tiger body (orange circle)
    draw.ellipse([88, 88, 168, 168], fill=(255, 140, 0))
    draw.ellipse([96, 96, 160, 160], fill=(255, 179, 102))
    
    # Draw stripes
    draw.rectangle([110, 100, 115, 130], fill=(51, 51, 51))
    draw.rectangle([125, 95, 130, 135], fill=(51, 51, 51))
    draw.rectangle([140, 100, 145, 130], fill=(51, 51, 51))
    
    # Draw eyes
    draw.ellipse([108, 108, 114, 114], fill=(51, 51, 51))
    draw.ellipse([142, 108, 148, 114], fill=(51, 51, 51))
    
    # Draw nose
    draw.ellipse([124, 125, 132, 133], fill=(255, 102, 0))
    
    img.save(f'{output_dir}/animal_tiger.png', 'PNG')
    print("✓ Created Tiger image")

def create_elephant_image():
    """Create stylized Elephant image"""
    img = Image.new('RGB', (256, 256), color=(169, 169, 169))
    draw = ImageDraw.Draw(img, 'RGBA')
    
    # Draw gradient background effect
    for i in range(256):
        shade = int(200 * (1 - i/256)) + 55
        draw.line([(0, i), (256, i)], fill=(shade, shade, shade))
    
    # Draw elephant body
    draw.ellipse([80, 100, 176, 176], fill=(192, 192, 192))
    
    # Draw elephant head
    draw.ellipse([95, 70, 161, 120], fill=(211, 211, 211))
    
    # Draw trunk (curved)
    draw.arc([110, 110, 150, 180], 0, 180, fill=(169, 169, 169), width=15)
    
    # Draw ears
    draw.ellipse([60, 100, 95, 150], fill=(176, 176, 176))
    draw.ellipse([161, 100, 196, 150], fill=(176, 176, 176))
    
    # Draw eyes
    draw.ellipse([115, 85, 120, 90], fill=(51, 51, 51))
    draw.ellipse([136, 85, 141, 90], fill=(51, 51, 51))
    
    img.save(f'{output_dir}/animal_elephant.png', 'PNG')
    print("✓ Created Elephant image")

def create_leopard_image():
    """Create stylized Leopard image"""
    img = Image.new('RGB', (256, 256), color=(212, 165, 116))
    draw = ImageDraw.Draw(img, 'RGBA')
    
    # Draw gradient background effect
    for i in range(256):
        shade = int(212 * (1 - i/256)) + 80
        draw.line([(0, i), (256, i)], fill=(shade, 165, 116))
    
    # Draw leopard body
    draw.ellipse([85, 95, 171, 165], fill=(244, 164, 96))
    
    # Draw spots
    spots = [(100, 110), (135, 115), (115, 135), (145, 140), (125, 150)]
    for spot in spots:
        draw.ellipse([spot[0]-8, spot[1]-8, spot[0]+8, spot[1]+8], fill=(139, 69, 19))
    
    # Draw head
    draw.ellipse([100, 70, 156, 115], fill=(218, 165, 32))
    
    # Draw eyes
    draw.ellipse([115, 85, 120, 90], fill=(255, 215, 0))
    draw.ellipse([136, 85, 141, 90], fill=(255, 215, 0))
    draw.ellipse([116, 86, 119, 89], fill=(51, 51, 51))
    draw.ellipse([137, 86, 140, 89], fill=(51, 51, 51))
    
    # Draw nose
    draw.ellipse([126, 100, 130, 104], fill=(139, 69, 19))
    
    img.save(f'{output_dir}/animal_leopard.png', 'PNG')
    print("✓ Created Leopard image")

def create_bird_image():
    """Create stylized Birds image"""
    img = Image.new('RGB', (256, 256), color=(74, 144, 226))
    draw = ImageDraw.Draw(img, 'RGBA')
    
    # Draw gradient sky background
    for i in range(256):
        shade = int(74 * (1 - i/256)) + 50
        draw.line([(0, i), (256, i)], fill=(shade, 150, 226))
    
    # Draw bird body (brown)
    draw.ellipse([100, 110, 156, 160], fill=(139, 69, 19))
    
    # Draw wing
    draw.polygon([(120, 130), (150, 120), (155, 135)], fill=(101, 67, 33))
    
    # Draw head
    draw.ellipse([138, 95, 160, 117], fill=(101, 67, 33))
    
    # Draw eye
    draw.ellipse([148, 102, 153, 107], fill=(255, 215, 0))
    draw.ellipse([149, 103, 152, 106], fill=(51, 51, 51))
    
    # Draw beak
    draw.polygon([(158, 105), (170, 103), (158, 107)], fill=(255, 140, 0))
    
    # Draw tail feathers (in flight style)
    draw.polygon([(140, 150), (120, 160), (125, 155)], fill=(139, 69, 19))
    draw.polygon([(145, 155), (150, 170), (155, 160)], fill=(139, 69, 19))
    
    img.save(f'{output_dir}/animal_bird.png', 'PNG')
    print("✓ Created Bird image")

def create_lion_image():
    """Create stylized Lion image"""
    img = Image.new('RGB', (256, 256), color=(218, 165, 32))
    draw = ImageDraw.Draw(img, 'RGBA')
    
    # Draw gradient background
    for i in range(256):
        shade = int(218 * (1 - i/256)) + 60
        draw.line([(0, i), (256, i)], fill=(shade, 165, 32))
    
    # Draw mane (dark circle background)
    draw.ellipse([70, 60, 186, 176], fill=(139, 69, 19))
    
    # Draw face
    draw.ellipse([95, 100, 161, 156], fill=(255, 215, 0))
    
    # Draw ears
    draw.ellipse([85, 70, 110, 95], fill=(139, 69, 19))
    draw.ellipse([146, 70, 171, 95], fill=(139, 69, 19))
    draw.ellipse([90, 75, 105, 90], fill=(255, 215, 0))
    draw.ellipse([151, 75, 166, 90], fill=(255, 215, 0))
    
    # Draw eyes
    draw.ellipse([115, 115, 120, 120], fill=(51, 51, 51))
    draw.ellipse([136, 115, 141, 120], fill=(51, 51, 51))
    
    # Draw nose
    draw.ellipse([126, 128, 130, 132], fill=(139, 69, 19))
    
    # Draw mouth
    draw.line([(128, 132), (128, 140)], fill=(139, 69, 19), width=2)
    
    img.save(f'{output_dir}/animal_lion.png', 'PNG')
    print("✓ Created Lion image")

def create_bear_image():
    """Create stylized Bear image"""
    img = Image.new('RGB', (256, 256), color=(139, 69, 19))
    draw = ImageDraw.Draw(img, 'RGBA')
    
    # Draw gradient background
    for i in range(256):
        shade = int(139 * (1 - i/256)) + 60
        draw.line([(0, i), (256, i)], fill=(shade, 69, 19))
    
    # Draw bear body
    draw.ellipse([85, 110, 171, 180], fill=(160, 82, 45))
    
    # Draw head
    draw.ellipse([95, 70, 161, 130], fill=(139, 69, 19))
    
    # Draw ears
    draw.ellipse([85, 60, 110, 85], fill=(139, 69, 19))
    draw.ellipse([146, 60, 171, 85], fill=(139, 69, 19))
    draw.ellipse([90, 65, 105, 80], fill=(101, 67, 33))
    draw.ellipse([151, 65, 166, 80], fill=(101, 67, 33))
    
    # Draw snout
    draw.ellipse([105, 105, 151, 135], fill=(154, 108, 74))
    
    # Draw eyes
    draw.ellipse([117, 90, 122, 95], fill=(0, 0, 0))
    draw.ellipse([134, 90, 139, 95], fill=(0, 0, 0))
    
    # Draw nose
    draw.ellipse([126, 115, 130, 119], fill=(0, 0, 0))
    
    # Draw mouth
    draw.line([(128, 119), (128, 125)], fill=(0, 0, 0), width=2)
    
    img.save(f'{output_dir}/animal_bear.png', 'PNG')
    print("✓ Created Bear image")

def create_other_animals():
    """Create placeholder images for other animals"""
    animals = {
        'boar': (218, 180, 140),
        'crocodile': (74, 124, 89),
        'deer': (205, 133, 63),
        'gaur': (105, 105, 105),
        'monkey': (218, 165, 32),
        'peacock': (65, 105, 225),
        'snake': (34, 139, 34),
        'wolf': (105, 105, 105)
    }
    
    for animal, color in animals.items():
        img = Image.new('RGB', (256, 256), color=color)
        draw = ImageDraw.Draw(img, 'RGBA')
        
        # Simple gradient background effect
        for i in range(256):
            shade = int(200 * (1 - i/256)) + 50
            draw.line([(0, i), (256, i)], fill=(shade, shade, shade))
        
        # Simple animal silhouette (circle + smaller elements)
        draw.ellipse([100, 110, 156, 160], fill=color)
        draw.ellipse([110, 75, 146, 115], fill=color)
        
        img.save(f'{output_dir}/animal_{animal}.png', 'PNG')
        print(f"✓ Created {animal.capitalize()} image")

if __name__ == "__main__":
    print("Generating AI-style animal images...\n")
    create_tiger_image()
    create_elephant_image()
    create_leopard_image()
    create_bird_image()
    create_lion_image()
    create_bear_image()
    create_other_animals()
    print(f"\n✓ All images generated successfully in {output_dir}")
