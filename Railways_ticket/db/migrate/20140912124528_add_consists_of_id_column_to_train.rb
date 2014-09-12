class AddConsistsOfIdColumnToTrain < ActiveRecord::Migration
  def change
    add_column :trains, :consists_of_id, :integer
  end
end
